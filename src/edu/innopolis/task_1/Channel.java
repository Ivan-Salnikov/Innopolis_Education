package edu.innopolis.task_1;

import java.util.ArrayList;

public class Channel {
    private String name;
    private ArrayList<Program> programs;

    public Channel(String name) {
        this.name = name;
        programs = new ArrayList<>();
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
