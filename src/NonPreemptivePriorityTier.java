import java.util.List;

public class NonPreemptivePriorityTier {
    private List<Process> queue;
    private int agingInterval = 4;

    public NonPreemptivePriorityTier() {}

    // الفرد 3: Tier 2 (Non-Preemptive Priority)
    public void addDemotedProcess(Process p, int currentTime) {}
    public Process getNextProcess(int currentTime) { return null; }
    public boolean isEmpty() { return true; }
    public void executeOneTick(Process current) {}
    public String getQueueState() { return ""; }

    // الفرد 5: Tier 3 (Aging)
    public void applyAging(int currentTime) {}
}