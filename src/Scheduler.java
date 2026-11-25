import java.util.List;

public abstract class Scheduler {
    public abstract void simulate(List<Process> processes, int contextSwitch, int quantum);

    public void printMetrics(List<Process> processes) {
        if (processes.isEmpty()) {
            return;
        }
        double totWT = 0, totTAT = 0;

        System.out.printf("%-8s%-10s%-10s%-12s%-10s%n",
                "Process", "Arrival", "Finish", "Turnaround", "Waiting", "Response");

        for (Process p : processes) {

            p.turnaroundTime = p.completionTime - p.arrivalTime;
            p.waitingTime    = p.turnaroundTime - p.burstTime;

            totWT += p.waitingTime;
            totTAT += p.turnaroundTime;

            System.out.printf("%-8s%-10d%-10d%-12d%-10d%n", p.name, p.arrivalTime,
                    p.completionTime, p.turnaroundTime, p.waitingTime);

        }
        int numberOfProcesses = processes.size();
        System.out.printf("Average Turnaround Time: %.2f%n", totTAT / numberOfProcesses);
        System.out.printf("Average Waiting Time: %.2f%n", totWT / numberOfProcesses);
    }
}
