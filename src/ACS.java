import java.util.List;

public class ACS extends Scheduler {
    private RoundRobinTier tier1;
    private NonPreemptivePriorityTier tier2;
    private Process currentProcess;
    private int currentTime;
    private int csRemaining;

    @Override
    public void simulate(List<Process> processes, int contextSwitch, int quantum) {}

    private void enqueueArrivals(List<Process> processes) {}
    private void dispatchNext(int contextSwitch) {}
    private void printTimeStep() {}
}