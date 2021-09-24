package edu.innopolis.attestation01_reflection.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PrinterMapImplTest {
    Printer printer = new PrinterMapImpl();

    @Test
    void printFields_whenAllFieldExists_thenNoneExceptions() {
        Map<String, Integer> testMapObject = new HashMap<>();
        testMapObject.put("Mihail", 185);
        testMapObject.put("Alexandr", 175);
        testMapObject.put("Albert", 182);
        testMapObject.put("Marat", 184);
        testMapObject.put("Marsel", 169);
        testMapObject.put("Ivan", 192);

        Set<String> fieldsToOutput= new HashSet<>();
        final String outputField1 = "Ivan";
        final String outputField2 = "Alexandr";
        final String outputField3 = "Albert";
        fieldsToOutput.add(outputField1);
        fieldsToOutput.add(outputField2);
        fieldsToOutput.add(outputField3);

        printer.printFields(testMapObject, fieldsToOutput);
        Assertions.assertTrue(true);
    }

    @Test
    void printFields_whenAnyFieldNotExists_thenThrowIllegalArgumentException() {
        Map<String, Integer> testMapObject = new HashMap<>();
        testMapObject.put("Mihail", 185);
        testMapObject.put("Alexandr", 175);
        testMapObject.put("Albert", 182);
        testMapObject.put("Marat", 184);
        testMapObject.put("Marsel", 169);
        testMapObject.put("Ivan", 192);

        Set<String> fieldsToOutput= new HashSet<>();
        final String outputField1 = "Ivan";
        final String outputField2 = "Alexandr";
        final String outputField3 = "Albert";
        fieldsToOutput.add(outputField1);
        fieldsToOutput.add(outputField2);
        fieldsToOutput.add(outputField3);
        fieldsToOutput.add("Mistake");  //java.lang.IllegalArgumentException: Поле "Mistake" не найдено

        Assertions.assertThrows(IllegalArgumentException.class,() -> printer.printFields(testMapObject,fieldsToOutput));

    }

}