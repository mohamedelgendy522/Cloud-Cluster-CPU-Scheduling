import java.util.LinkedList;
import java.util.Queue;

public class RoundRobinTier {

    private final Queue<Process> queue = new LinkedList<>();
    private final int quantum;
    
    public RoundRobinTier(int quantum) {
        this.quantum = quantum;

    }

    public void add(Process p)      { p.setCurrentTier(1); queue.add(p); }
    public Process pollNext()       { return queue.poll(); }
    public boolean isEmpty()        { return queue.isEmpty(); }
    public int getQuantum()         { return quantum; }


    public boolean shouldDemote(Process p, int quantumUsed) {
        return quantumUsed >= quantum && p.getRemainingTime() > 0;
    }

    public String getQueueState() {            
        if (queue.isEmpty()) return "Tier 1: [Empty]";
        StringBuilder sb = new StringBuilder("Tier 1: [");
        boolean first = true;
        for (Process p : queue) {
            if (!first) sb.append(", ");
            sb.append(p.getName()).append(" (remaining: ").append(p.getRemainingTime())
                    .append(", Priority: ").append(p.getEffectivePriority()).append(")");
            first = false;
        }
        return sb.append("]").toString();
    }
}
