package edu.innopolis.attestation01_reflection.services;

import java.util.Set;

public interface Printer {
    <T> void printFields(T object, Set<String> outputFields);
}
