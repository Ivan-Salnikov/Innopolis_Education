package edu.innopolis.task_4_exceptions.exceptions;

public class BadEmailException extends RuntimeException{

    public BadEmailException(String message) {
        super(message);
    }
}
