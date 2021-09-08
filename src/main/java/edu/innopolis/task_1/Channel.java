package edu.innopolis.task_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Channel {
    private String name;
    private List<Program> programs;

    public Channel(String name) {
        this.name = name;
        programs = new ArrayList<>();
    }

    public Channel(String name, Program ...programs) {
        this.name = name;
        this.programs = Arrays.asList(programs);
    }

    public String getName() {

        return name;
    }

    public void addProgram (Program program) {

        programs.add(program);
        program.setChannel(this);
    }

    public int getProgramCount() {
        return programs.size();
    }

    public String getCurrentProgramName(int numberProgram) {

        return programs.get(numberProgram).getName();
    }
}
