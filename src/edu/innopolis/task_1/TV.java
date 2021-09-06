package edu.innopolis.task_1;

import java.util.ArrayList;

public class TV {
    private String mark;
    private ArrayList<Channel> channels;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private Controller controller;

    public TV(String mark) {
        this.mark = mark;
        channels = new ArrayList<>();
    }

    public void addChannel (Channel channel) {

        channels.add(channel);
    }

    public String getMark() {
        return mark;
    }
}
