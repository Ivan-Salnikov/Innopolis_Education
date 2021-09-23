package edu.innopolis.attestation01_reflection;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AttestationReflectionTest {

    @Test
    void cleanUp_ObjectNotExtendsMap() {
        ArrayList<String> listString = new ArrayList<>();
        listString.add("String_1");
        listString.add("String_2");
        Map<String, Integer> mapStringInteger = new HashMap<>();
        mapStringInteger.put("Some_String_1", 279);
        mapStringInteger.put("Some_String_2", 0);

        TestObject testObject = new TestObject(25, listString, mapStringInteger, "Test string",
                100_000L, (byte) 1, 25_000d, 123456789, true,
                12345.569f, 'A', (short) 123);
//        private Integer objectInteger;
//        private ArrayList<String> objectArrayList;
//        private Map <String, Integer> objectMap;
//        private String someString;
//        private long primitiveLong;
//        private byte primitiveByte;
//        private double primitiveDouble;
//        private int primitiveInt;
//        private boolean primitiveBoolean;
//        private float primitiveFloat;
//        private char primitiveChar;
//        private short primitiveShort;
//
        AttestationReflection attestationReflection = new AttestationReflection();

        Set<String> fieldsToCleanUp = new HashSet<>();
        fieldsToCleanUp.add("objectInteger");
        fieldsToCleanUp.add("objectArrayList");
        fieldsToCleanUp.add("objectMap");
        fieldsToCleanUp.add("someString");
        fieldsToCleanUp.add("primitiveLong");
        fieldsToCleanUp.add("primitiveByte");
        fieldsToCleanUp.add("primitiveDouble");
        fieldsToCleanUp.add("primitiveInt");
        fieldsToCleanUp.add("primitiveBoolean");
        fieldsToCleanUp.add("primitiveFloat");
        fieldsToCleanUp.add("primitiveChar");
        fieldsToCleanUp.add("primitiveShort");


        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToCleanUp.add("arrayListObject");
        fieldsToCleanUp.add("mapObject");
        fieldsToCleanUp.add("integerObject");

        attestationReflection.cleanUp(testObject, fieldsToCleanUp, fieldsToOutput);

    }
}