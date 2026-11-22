public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    public int initialPriority;

    public int remainingTime;
    public int effectivePriority;

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setInitialPriority(int initialPriority) {
        this.initialPriority = initialPriority;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setEffectivePriority(int effectivePriority) {
        this.effectivePriority = effectivePriority;
    }

    public void setCurrentTier(int currentTier) {
        this.currentTier = currentTier;
    }

    public void setTier2EnterTime(int tier2EnterTime) {
        this.tier2EnterTime = tier2EnterTime;
    }

    public void setFirstStartTime(int firstStartTime) {
        this.firstStartTime = firstStartTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int currentTier;
    public int tier2EnterTime;
    public int firstStartTime;
    public int finishTime;

    public Process(String name, int arrivalTime, int burstTime, int initialPriority) {}
    public Process(Process other) {}

    public String getName() {
        return name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getInitialPriority() {
        return initialPriority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public int getEffectivePriority() {
        return effectivePriority;
    }

    public int getCurrentTier() {
        return currentTier;
    }

    public int getTier2EnterTime() {
        return tier2EnterTime;
    }

    public int getFirstStartTime() {
        return firstStartTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
}