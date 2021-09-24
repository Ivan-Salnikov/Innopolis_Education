package edu.innopolis.attestation01_reflection.services;

import java.util.HashSet;
import java.util.Set;

public interface ExecutorService {
    void validateFields(Object object, Set<String> allFields);
    void cleanFields(Object object, Set<String> cleanFields);
    static Set<String> collectFields(Set<String> ... fields){
        Set<String> collectSet = new HashSet<>();
        for (Set<String> set : fields) {
            collectSet.addAll(set);
        }
        return collectSet;
    }
}
