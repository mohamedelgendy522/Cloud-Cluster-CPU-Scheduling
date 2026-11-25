import java.util.ArrayList;
import java.util.List;

public class NonPreemptivePriorityTier {
    private List<Process> queue;
    private int agingInterval = 4;
    private Process runningProcess = null;
    private int lastKnownTime = 0;

    public NonPreemptivePriorityTier() {
        this.queue = new ArrayList<>();
    }

    public void addDemotedProcess(Process p, int currentTime) {
        if (p == null || p.getRemainingTime() <= 0) {
            return;
        }
        this.lastKnownTime = currentTime;
        p.setCurrentTier(2);
        p.setTier2EnterTime(currentTime);
        if (!queue.contains(p)) {
            queue.add(p);
        }
    }

    private boolean isHigherPriority(Process p1, Process p2) {
    return p1.getEffectivePriority() < p2.getEffectivePriority();
    }

    public Process getNextProcess(int currentTime) {
        this.lastKnownTime = currentTime;

        if (runningProcess != null && runningProcess.getRemainingTime() > 0 && queue.contains(runningProcess)) {
            return runningProcess;
        }

        Process best = null;
        for (Process p : queue) {
            if (p.getRemainingTime() <= 0) {
                continue;
            }
            if (best == null || isHigherPriority(p, best)) {
                best = p;
            }
        }

        runningProcess = best;
        return runningProcess;
    }

    public boolean isEmpty() {
        for (Process p : queue) {
            if (p.getRemainingTime() > 0) {
                return false;
            }
        }
        return true;
    }

    public void executeOneTick(Process current) {
        if (current == null || current.getRemainingTime() <= 0) {
            return;
        }

        runningProcess = current;

        if (current.getFirstStartTime() == -1) {
            current.setFirstStartTime(lastKnownTime);
        }

        current.setRemainingTime(current.getRemainingTime() - 1);
        lastKnownTime++;

        if (current.getRemainingTime() == 0) {
            current.setCompletionTime(lastKnownTime);
            queue.remove(current);
            if (runningProcess == current) {
                runningProcess = null;
            }
        }
    }

    public String getQueueState() {
        if (queue.isEmpty()) {
            return "Tier 2: [Empty]";
        }
        StringBuilder sb = new StringBuilder("Tier 2: [");
        boolean first = true;
        for (Process p : queue) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(p.getName())
              .append(" (remaining: ").append(p.getRemainingTime())
              .append(", Priority: ").append(p.getEffectivePriority())
              .append(")");
            first = false;
        }
        return sb.append("]").toString();
    }

    public void applyAging(int currentTime) {
    this.lastKnownTime = currentTime;

    for (Process p : queue) {

        if (p == runningProcess) {
            continue;
        }

        int waitingTime = currentTime - p.getTier2EnterTime();

        int agingSteps = waitingTime / agingInterval;

        int newPriority = p.getInitialPriority() - agingSteps;

        if (newPriority < 0) {
            newPriority = 0;
        }

        p.setEffectivePriority(newPriority);
    }
}
}
