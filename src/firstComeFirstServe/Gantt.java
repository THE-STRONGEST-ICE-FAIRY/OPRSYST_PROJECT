package firstComeFirstServe;

import java.util.LinkedList;

public class Gantt {
    LinkedList<Program> programList;
    LinkedList<Program> queue;
    LinkedList<Time> time;

    Gantt(LinkedList<Program> programList) {
        this.programList = new LinkedList<>();
        for (Program p : programList) this.programList.add(p.clone());

        queue = new LinkedList<>();
        time = new LinkedList<>();

        process();
    }

    private void process() {
        // @Ornedo
    }
}
