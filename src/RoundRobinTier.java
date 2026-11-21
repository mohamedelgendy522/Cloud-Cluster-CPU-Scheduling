import java.util.Queue;

public class RoundRobinTier {
    private Queue<Process> queue;
    private int quantum;
    private int quantumUsed;

    public RoundRobinTier(int quantum) {}

    public void addProcess(Process p) {}
    public Process getNextProcess() { return null; }
    public boolean isEmpty() { return true; }
    public boolean executeOneTick(Process current) { return false; }
    public String getQueueState() { return ""; }
}