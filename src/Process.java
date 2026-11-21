public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    public int initialPriority;

    public int remainingTime;
    public int effectivePriority;
    public int currentTier;
    public int tier2EnterTime;
    public int firstStartTime;
    public int finishTime;

    public Process(String name, int arrivalTime, int burstTime, int initialPriority) {}
    public Process(Process other) {}
}