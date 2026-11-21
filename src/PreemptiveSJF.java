import java.util.List;

public class PreemptiveSJF extends Scheduler {
    private int currentTime;
    private int csRemaining;
    private Process currentProcess;

    @Override
    public void simulate(List<Process> processes, int contextSwitch, int quantum) {}

    private Process selectShortestRemaining(List<Process> readyProcesses) { return null; }
}