package edu.innopolis.attestation01_reflection.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PrinterObjectImplTest {
    Printer printer = new PrinterObjectImpl();

    @Test
    void printFields_whenAllFieldExists_thenNoneExceptions() {
        final String testString = "Test string";
        final double testDouble = 25_000d;
        ArrayList<String> listString = new ArrayList<>();
        listString.add("String_1");
        listString.add("String_2");
        Map<String, Integer> mapStringInteger = new HashMap<>();
        mapStringInteger.put("Some_String_1", 279);
        mapStringInteger.put("Some_String_2", 0);

        TestObject testObject = new TestObject(25, listString, mapStringInteger, testString,
                100_000L, (byte) 1, testDouble, 123456789, true,
                12345.569f, 'A', (short) 123);


        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("objectArrayList");
        fieldsToOutput.add("someString");
        fieldsToOutput.add("primitiveDouble");

        printer.printFields(testObject, fieldsToOutput);
        Assertions.assertTrue(true);
    }

    @Test
    void printFields_whenAnyFieldNotExists_thenThrowIllegalArgumentException() {

        final String testString = "Test string";
        final double testDouble = 25_000d;
        ArrayList<String> listString = new ArrayList<>();
        listString.add("String_1");
        listString.add("String_2");
        Map<String, Integer> mapStringInteger = new HashMap<>();
        mapStringInteger.put("Some_String_1", 279);
        mapStringInteger.put("Some_String_2", 0);

        TestObject testObject = new TestObject(25, listString, mapStringInteger, testString,
                100_000L, (byte) 1, testDouble, 123456789, true,
                12345.569f, 'A', (short) 123);


        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("objectArrayList");
        fieldsToOutput.add("someString");
        fieldsToOutput.add("primitiveDouble");
        fieldsToOutput.add("Mistake");  //java.lang.IllegalArgumentException: Поле "Mistake" не найдено

        Assertions.assertThrows(IllegalArgumentException.class,() -> printer.printFields(testObject, fieldsToOutput));
    }
}