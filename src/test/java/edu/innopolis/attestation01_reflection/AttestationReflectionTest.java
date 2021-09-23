package edu.innopolis.attestation01_reflection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AttestationReflectionTest {

    @Test
    void cleanUp_ObjectNotExtendsMap() {
        TestObject testObject = new TestObject();
        AttestationReflection attestationReflection = new AttestationReflection();

        Set<String> fieldsToCleanUp = new HashSet<>();
        fieldsToCleanUp.add("integerObject");
        fieldsToCleanUp.add("longPrimitive");
        fieldsToCleanUp.add("arrayListObject");
        fieldsToCleanUp.add("mapObject");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanUp.add("arrayListObject");
        fieldsToCleanUp.add("mapObject");
        fieldsToCleanUp.add("integerObject");

        attestationReflection.cleanUp(testObject, fieldsToCleanUp, fieldsToOutput);

    }
}