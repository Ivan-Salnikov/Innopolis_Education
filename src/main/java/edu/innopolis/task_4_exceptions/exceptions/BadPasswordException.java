package edu.innopolis.task_4_exceptions.exceptions;

public class BadPasswordException extends RuntimeException{

    public BadPasswordException(String message) {
        super(message);
    }
}
