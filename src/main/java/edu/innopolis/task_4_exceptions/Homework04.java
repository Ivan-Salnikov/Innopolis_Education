package edu.innopolis.task_4_exceptions;

public class Homework04 {
    public static void main(String[] args) {


        ZoomUsersService zoomUsersService = new ZoomUsersService();

        zoomUsersService.signUp("d@d.dd", "88gg77889G");
        zoomUsersService.signUp("d2@d.dd", "KLKLKLKLKL8k");

        zoomUsersService.signIn("d@d.dd", "88gg77889G");
        //zoomUsersService.signIn("d@d.dd", "KLKLKLKLKL8k");

    }
}
