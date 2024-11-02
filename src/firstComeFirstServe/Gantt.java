package firstComeFirstServe;

import roundRobin.Program;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;

public class Gantt {
    public LinkedList<Program> programList, programListCopy;
    public LinkedList<Program> queue;
    public LinkedList<Time> time;

    public Gantt(LinkedList<Program> programList) {
        this.programList = new LinkedList<>();
        this.programListCopy = new LinkedList<>();
        for (Program p : programList) {
            this.programList.add(p.clone());
            programListCopy.add(p.clone());
        }

        queue = new LinkedList<>();
        time = new LinkedList<>();

        process();
    }

    private void process() {
        // Sort by arrival time
        programList.sort(Comparator.comparing(Program::getTimeIn));

        int i = 0;
        do {
            time.add(new Time());

            while (!programList.isEmpty() && programList.getFirst().getTimeIn() == time.size() - 1) queue.add(programList.pop());

            if (time.size() > 1 && time.get(time.size() - 2).getProgram() != null) {
                if (time.get(time.size() - 2).getProgram().getDuration() <= 0) {
                    if (!queue.isEmpty()) time.getLast().addProgram(queue.pop());
                }
                else time.getLast().setProgram(time.get(time.size() - 2).getProgram().clone());
            }
            else if (!queue.isEmpty()) time.getLast().addProgram(queue.pop());

            for (Program p : queue) p.addTimeQueued();

            System.out.println(i++ + "");
            if (time.getLast().getProgram() != null) {
                Program program = time.getLast().getProgram();
                System.out.println("\t" + program.getName());
                System.out.println("\t\tTime in:  " + program.getTimeIn());
                System.out.println("\t\tDuration: " + program.getDuration());
            }
            else System.out.println("\tEMPTY");
            System.out.println("\tQueue: (" + queue.size() + ")");
            if (!queue.isEmpty()) for (Program p : queue) {
                System.out.println("\t\t" + p.getName());
                System.out.println("\t\t\tTime in:  " + p.getTimeIn());
                System.out.println("\t\t\tDuration: " + p.getDuration());
            }
            System.out.println("\tProgram List: (" + programList.size() + ")");
            if (!programList.isEmpty()) for (Program p : programList) {
                System.out.println("\t\t" + p.getName());
                System.out.println("\t\t\tTime in:  " + p.getTimeIn());
                System.out.println("\t\t\tDuration: " + p.getDuration());
            }
            System.out.println();

            if (time.getLast().getProgram() != null) {
                time.getLast().getProgram().setDuration(time.getLast().getProgram().getDuration() - 1);
                if (time.getLast().getProgram().getDuration() <= 0) {
                    time.getLast().getProgram().setTimeOut(time.size());
                    for (Program p : programListCopy) if (Objects.equals(p.getName(), time.getLast().getProgram().getName())) {
                        p.setTimeOut(time.size());
                        p.setTimeQueued(time.getLast().getProgram().getTimeQueued());
                        break;
                    }
                }
            }
        }
        while (!queue.isEmpty() || !programList.isEmpty() ||
                (
                        !time.isEmpty() &&
                                time.getLast().getProgram() != null &&
                                time.getLast().getProgram().getDuration() >= 0
                )
        );

        // Analyze the results
        double timeQueuedTotal = 0;
        double timeElapsedTotal = 0;
        System.out.println("Program List Analysis: (" + programListCopy.size() + ")");
        if (!programListCopy.isEmpty()) {
            for (Program p : programListCopy) {
                System.out.println("\t" + p.getName());
                System.out.println("\t\tTime in:  " + p.getTimeIn());
                System.out.println("\t\tDuration: " + p.getDuration());
                System.out.println("\t\tTime out: " + p.getTimeOut());
                System.out.println("\t\tTime queued: " + p.getTimeQueued());
                timeQueuedTotal += p.getTimeQueued();
                int timeElapsed = p.getTimeOut() - p.getTimeIn();
                System.out.println("\t\tTime elapsed: " + timeElapsed);
                timeElapsedTotal += timeElapsed;
            }
        }
        System.out.println();
        System.out.println("\tTime Queued Average: " + (timeQueuedTotal / programListCopy.size()));
        System.out.println("\tTime Elapsed Average: " + (timeElapsedTotal / programListCopy.size()));
        System.out.println();
    }
}
