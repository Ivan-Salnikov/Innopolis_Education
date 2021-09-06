package edu.innopolis.task_1;

public class Program {
    private String name;
    private Channel channel;

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Program(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
