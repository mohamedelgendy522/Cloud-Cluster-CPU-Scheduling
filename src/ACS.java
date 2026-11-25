import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class ACS extends Scheduler {
    private RoundRobinTier tier1;
    private NonPreemptivePriorityTier tier2;
    private Process currentProcess;
    private Process lastExecuted;
    private int currentTime;
    private int csRemaining;
    private int quantumUsed;
    private int completed;

    @Override
    public void simulate(List<Process> processes, int contextSwitch, int quantum) {
        if (quantum <= 0 || contextSwitch < 0) {
            throw new IllegalArgumentException("Quantum must be positive and context switch must be non-negative.");
        }

        List<Process> run = new ArrayList<>();
        for (Process p : processes) {
            if (p.getArrivalTime() < 0 || p.getBurstTime() <= 0) {
                throw new IllegalArgumentException("Invalid arrival or burst time for " + p.getName());
            }
            run.add(new Process(p));
        }
        // Keep the metrics in input order; simultaneous arrivals also keep their input order.
        List<Process> arrivalOrder = new ArrayList<>(run);
        arrivalOrder.sort(Comparator.comparingInt(Process::getArrivalTime));
        Queue<Process> arrivals = new ArrayDeque<>(arrivalOrder);

        tier1 = new RoundRobinTier(quantum);
        tier2 = new NonPreemptivePriorityTier();
        currentProcess = null;
        lastExecuted = null;
        csRemaining = 0;
        completed = 0;

        for (currentTime = 0; completed < run.size(); currentTime++) {
            enqueueArrivals(arrivals);
            tier2.applyAging(currentTime);
            if (currentProcess == null) {
                dispatchNext(contextSwitch);
            }

            if (currentProcess == null && arrivals.isEmpty()) {
                throw new IllegalStateException("Time " + currentTime
                        + ": unfinished work, but neither tier returned a process. Check Tier 2 demotion.");
            }
            printTimeStep();
            if (csRemaining > 0) {
                csRemaining--;
            } else if (currentProcess == null) {
                lastExecuted = null;
            } else {
                executeCurrentTick();
            }
        }
        printMetrics(run);
    }

    private void enqueueArrivals(Queue<Process> arrivals) {
        while (!arrivals.isEmpty() && arrivals.peek().getArrivalTime() == currentTime) {
            tier1.add(arrivals.remove());
        }
    }

    private void dispatchNext(int contextSwitch) {
        currentProcess = tier1.pollNext();
        if (currentProcess == null && !tier2.isEmpty()) {
            currentProcess = tier2.getNextProcess(currentTime);
        }
        quantumUsed = 0;
        if (currentProcess != null && lastExecuted != null && currentProcess != lastExecuted) {
            csRemaining = contextSwitch;
        }
    }

    private void executeCurrentTick() {
        if (currentProcess.getFirstStartTime() == -1) {
            currentProcess.setFirstStartTime(currentTime);
        }
        lastExecuted = currentProcess;
        boolean inTier1 = currentProcess.getCurrentTier() == 1;
        if (inTier1) {
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
            quantumUsed++;
        } else {
            tier2.executeOneTick(currentProcess);
        }

        // A tick starting at t finishes at t + 1, even when it uses the last unit of the quantum.
        int endTime = currentTime + 1;
        if (currentProcess.getRemainingTime() == 0) {
            currentProcess.setCompletionTime(endTime);
            completed++;
            currentProcess = null;
        } else if (inTier1 && tier1.shouldDemote(currentProcess, quantumUsed)) {
            currentProcess.setCurrentTier(2);
            currentProcess.setTier2EnterTime(endTime);
            tier2.addDemotedProcess(currentProcess, endTime);
            currentProcess = null;
        }
    }

    private void printTimeStep() {
        String cpu = "IDLE";
        if (csRemaining > 0) {
            cpu = "CS " + lastExecuted.getName() + " -> " + currentProcess.getName();
        } else if (currentProcess != null) {
            cpu = currentProcess.getName() + " (Tier " + currentProcess.getCurrentTier() + ")";
        }
        String tier2State = tier2.isEmpty() ? "Tier 2: [Empty]" : tier2.getQueueState();
        System.out.printf("Time %d | CPU: %s | %s | %s%n", currentTime, cpu, tier1.getQueueState(), tier2State);
    }
}
