package edu.innopolis.attestation01_reflection.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ExecutorServiceMapImplTest {
    private static ExecutorService executorService = new ExecutorServiceMapImpl();

    @Test
    void validateFields_whenAllFieldExists_thenNoneExceptions() {
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

        Set<String> fieldsToCleanUp= new HashSet<>();
        final String cleanupField1 = "Marat";
        final String cleanupField2 = "Marsel";
        fieldsToCleanUp.add(cleanupField1);
        fieldsToCleanUp.add(cleanupField2);

        executorService.validateFields(testMapObject,
                ExecutorService.collectFields(fieldsToCleanUp, fieldsToOutput));
        Assertions.assertTrue(true);
    }

    @Test
    void validateFields_whenAnyFieldNotExists_thenThrowIllegalArgumentException() {
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

        Set<String> fieldsToCleanUp= new HashSet<>();
        final String cleanupField1 = "Marat";
        final String cleanupField2 = "Marsel";
        fieldsToCleanUp.add(cleanupField1);
        fieldsToCleanUp.add(cleanupField2);
        fieldsToCleanUp.add("Mistake");  //java.lang.IllegalArgumentException: Поле "Mistake" не найдено

        Assertions.assertThrows(IllegalArgumentException.class,() -> executorService.validateFields(testMapObject,
                ExecutorService.collectFields(fieldsToCleanUp, fieldsToOutput)));
    }


    @Test
    void cleanFields_whenAllFieldExists_thenNoneExceptions() {
        Map<String, Integer> testMapObject = new HashMap<>();
        testMapObject.put("Mihail", 185);
        testMapObject.put("Alexandr", 175);
        testMapObject.put("Albert", 182);
        testMapObject.put("Marat", 184);
        testMapObject.put("Marsel", 169);
        testMapObject.put("Ivan", 192);

        Set<String> fieldsToCleanUp= new HashSet<>();
        final String cleanupField1 = "Marat";
        final String cleanupField2 = "Marsel";
        fieldsToCleanUp.add(cleanupField1);
        fieldsToCleanUp.add(cleanupField2);

        executorService.cleanFields(testMapObject, fieldsToCleanUp);

        Assertions.assertFalse(testMapObject.containsKey(cleanupField1));
        Assertions.assertFalse(testMapObject.containsKey(cleanupField2));
        Assertions.assertTrue(testMapObject.containsKey("Alexandr"));
    }

    @Test
    void cleanFields_whenAnyFieldNotExists_thenThrowIllegalArgumentException() {
        Map<String, Integer> testMapObject = new HashMap<>();
        testMapObject.put("Mihail", 185);
        testMapObject.put("Alexandr", 175);
        testMapObject.put("Albert", 182);
        testMapObject.put("Marat", 184);
        testMapObject.put("Marsel", 169);
        testMapObject.put("Ivan", 192);

        Set<String> fieldsToCleanUp= new HashSet<>();
        final String cleanupField1 = "Marat";
        final String cleanupField2 = "Marsel";
        fieldsToCleanUp.add(cleanupField1);
        fieldsToCleanUp.add(cleanupField2);
        fieldsToCleanUp.add("Mistake");//java.lang.IllegalArgumentException: Поле "Mistake" не найдено

        Assertions.assertThrows(IllegalArgumentException.class,() ->
                executorService.cleanFields(testMapObject, fieldsToCleanUp));

        //Проверяем, что объект остался неизменным
        Assertions.assertTrue(testMapObject.containsKey(cleanupField1));
        Assertions.assertTrue(testMapObject.containsKey(cleanupField2));
    }
}