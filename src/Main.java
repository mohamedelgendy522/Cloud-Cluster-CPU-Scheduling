import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        testCase1();
        testCase2();

    }

    public static void testCase1() {

        System.out.println("\n========== TEST CASE 1 ==========\n");

        List<Process> processes = new ArrayList<>();

        processes.add(new Process("P1", 0, 4, 2));
        processes.add(new Process("P2", 1, 7, 3));
        processes.add(new Process("P3", 3, 2, 1));

        int quantum = 3;
        int contextSwitch = 0;

        ACS scheduler = new ACS();

        scheduler.simulate(processes, contextSwitch, quantum);
    }

    public static void testCase2() {

        System.out.println("\n========== TEST CASE 2 ==========\n");

        List<Process> processes = new ArrayList<>();

        processes.add(new Process("P1", 0, 5, 4));
        processes.add(new Process("P2", 1, 6, 2));
        processes.add(new Process("P3", 4, 4, 3));

        int quantum = 3;
        int contextSwitch = 1;

        ACS scheduler = new ACS();

        scheduler.simulate(processes, contextSwitch, quantum);
    }
}