package edu.innopolis.attestation01_reflection.services;

import java.util.Set;

public interface ExecutorService {
    void validateFields(Object object, Set<String> allFields);
    void cleanFields(Object object, Set<String> cleanFields);
}
