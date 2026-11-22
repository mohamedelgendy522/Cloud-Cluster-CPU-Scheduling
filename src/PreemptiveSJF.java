import java.util.ArrayList;
import java.util.List;

public class PreemptiveSJF extends Scheduler {
    private int currentTime;
    private int csRemaining;
    private Process currentProcess;

    @Override
    public void simulate(List<Process> processes, int contextSwitch, int quantum) {
        List<Process> simProcesses = new ArrayList<>();
        for (int i = 0; i < processes.size(); i++) {
            simProcesses.add(new Process(processes.get(i)));
        }

        List<Process> readyProcesses = new ArrayList<>();
        currentTime = 0;
        csRemaining = 0;
        currentProcess = null;
        Process lastProcess = null;
        int completed = 0;

        while (completed < simProcesses.size()) {
            for (int i = 0; i < simProcesses.size(); i++) {
                if (simProcesses.get(i).getArrivalTime() == currentTime) {
                    readyProcesses.add(simProcesses.get(i));
                }
            }

            if (csRemaining > 0) {
                System.out.println("Time " + currentTime + ": Context Switch");
                csRemaining--;
                currentTime++;
                continue;
            }

            Process next = selectShortestRemaining(readyProcesses);
            if (next == null) {
                System.out.println("Time " + currentTime + ": IDLE");
                lastProcess = null;
                currentTime++;
                continue;
            }

            if (next != currentProcess) {
                // لو فيه عملية تانية كانت شغالة قبلها وفيه وقت CS
                if (lastProcess != null && lastProcess != next && contextSwitch > 0) {
                    System.out.println("Time " + currentTime + ": Context Switch");
                    currentProcess = next;
                    lastProcess = next;
                    csRemaining = contextSwitch - 1;
                    currentTime++;
                    continue;
                }
                currentProcess = next;
            }

            if (currentProcess.getFirstStartTime() == -1) {
                currentProcess.setFirstStartTime(currentTime);
            }

            System.out.println("Time " + currentTime + ": " + currentProcess.getName());
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
            lastProcess = currentProcess;
            currentTime++;

            if (currentProcess.getRemainingTime() == 0) {
                currentProcess.setFinishTime(currentTime);
                readyProcesses.remove(currentProcess);
                completed++;
                currentProcess = null;
            }
        }

        printMetrics(simProcesses);
    }

    private Process selectShortestRemaining(List<Process> readyProcesses) {
        int mn = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < readyProcesses.size(); i++) {
            Process p = readyProcesses.get(i);
            if (p.getRemainingTime() <= 0) continue;

            if (p.getRemainingTime() < mn ||
                    (p.getRemainingTime() == mn && index != -1
                            && p.getArrivalTime() < readyProcesses.get(index).getArrivalTime())) {
                mn = p.getRemainingTime();
                index = i;
            }
        }
        if (index == -1) {
            return null;
        }
        return readyProcesses.get(index);
    }
}