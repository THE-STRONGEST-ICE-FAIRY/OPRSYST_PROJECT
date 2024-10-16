package firstComeFirstServe;

import roundRobin.Program;

public class Time {
    private roundRobin.Program program;

    public void addProgram(roundRobin.Program program) {
        this.program = program;
    }

    public roundRobin.Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
