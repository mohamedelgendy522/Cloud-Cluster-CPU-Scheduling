import java.util.List;

public abstract class Scheduler {
    public abstract void simulate(List<Process> processes, int contextSwitch, int quantum);

    public void printMetrics(List<Process> processes) {}
}