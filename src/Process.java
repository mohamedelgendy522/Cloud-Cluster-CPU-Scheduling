public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    public int initialPriority;
    public int effectivePriority;

    public int remainingTime;
    public int completionTime;
    public int turnaroundTime;
    public int waitingTime;

    public int currentTier;
    public int tier2EnterTime;
    public int firstStartTime;

    public Process(String name, int arrivalTime, int burstTime, int initialPriority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.initialPriority = initialPriority;
        this.effectivePriority = initialPriority;
        this.remainingTime = burstTime;
        this.currentTier = 1;
        this.firstStartTime = -1;
    }
    public Process(Process other) {
        this(other.name, other.arrivalTime, other.burstTime, other.initialPriority);
    }

    public void setName(String name) { this.name = name; }

    public void setArrivalTime(int arrivalTime) { this.arrivalTime = arrivalTime; }

    public void setBurstTime(int burstTime) { this.burstTime = burstTime; }

    public void setInitialPriority(int initialPriority) { this.initialPriority = initialPriority; }

    public void setRemainingTime(int remainingTime) { this.remainingTime = remainingTime; }

    public void setEffectivePriority(int effectivePriority) { this.effectivePriority = effectivePriority; }

    public void setCurrentTier(int currentTier) { this.currentTier = currentTier; }

    public void setTier2EnterTime(int tier2EnterTime) { this.tier2EnterTime = tier2EnterTime; }

    public void setFirstStartTime(int firstStartTime) { this.firstStartTime = firstStartTime; }

    public void setCompletionTime(int completionTime) { this.completionTime = completionTime; }

    public String getName() { return name; }

    public int getArrivalTime() { return arrivalTime; }

    public int getBurstTime() { return burstTime; }

    public int getInitialPriority() { return initialPriority; }

    public int getRemainingTime() { return remainingTime; }

    public int getEffectivePriority() { return effectivePriority; }

    public int getCurrentTier() { return currentTier; }

    public int getTier2EnterTime() { return tier2EnterTime; }

    public int getFirstStartTime() { return firstStartTime; }

    public int getCompletionTime() { return completionTime; }

    public int getResponseTime() { return firstStartTime - arrivalTime; }
}
