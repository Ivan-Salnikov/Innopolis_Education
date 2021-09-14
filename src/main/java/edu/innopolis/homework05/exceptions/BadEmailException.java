package edu.innopolis.homework05.exceptions;

public class BadEmailException extends RuntimeException{

    public BadEmailException(String message) {
        super(message);
    }
}
