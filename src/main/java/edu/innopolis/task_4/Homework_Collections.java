package edu.innopolis.task_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Homework_Collections {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.next();

        StringUtils stringUtils = new StringUtils(inputString);
        System.out.println(stringUtils.getMapToString());

    }
}
