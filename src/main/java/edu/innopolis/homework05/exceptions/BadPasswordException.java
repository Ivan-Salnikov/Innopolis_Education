package edu.innopolis.homework05.exceptions;

public class BadPasswordException extends RuntimeException{

    public BadPasswordException(String message) {
        super(message);
    }
}
