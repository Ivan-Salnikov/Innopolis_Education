package edu.innopolis.task_1;

import java.util.Random;

public class Controller {
    private TV tv;

    public Controller(TV tv) {

        this.tv = tv;
        tv.setController(this);
    }

    public void on(Channel channel) {
        Random random = new Random();
        int randomProgram = random.nextInt(channel.getProgramCount());

        System.out.println("На телевизоре \"" + tv.getMark() + "\" включен канал \"" + channel.getName() +
                "\", по которому идет передача \"" + channel.getCurrentProgramName(randomProgram) + "\"");
    }
}
