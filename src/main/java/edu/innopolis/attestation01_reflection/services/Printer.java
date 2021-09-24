package edu.innopolis.attestation01_reflection.services;

import java.util.Set;

public interface Printer {
    void printFields(Object object, Set<String> outputFields);
}
