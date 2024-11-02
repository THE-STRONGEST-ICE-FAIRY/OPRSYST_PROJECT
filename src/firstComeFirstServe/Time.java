package firstComeFirstServe;

import roundRobin.Program;

public class Time {
    private Program program;

    public void addProgram(Program program) {
        this.program = program;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
