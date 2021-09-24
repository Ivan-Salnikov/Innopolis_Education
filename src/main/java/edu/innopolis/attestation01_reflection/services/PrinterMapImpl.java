package edu.innopolis.attestation01_reflection.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class PrinterMapImpl implements Printer {
    @Override
    public <T> void printFields(T object, Set<String> outputFields) {

        ArrayList<String> outputFieldsToString = new ArrayList<>();

        for(String fieldOutput : outputFields) {
            try {
                Object value = null;
                value = object.getClass().getDeclaredMethod("get", Object.class).invoke(object, fieldOutput);
                if(value == null) {
                    throw new IllegalArgumentException("Поле \"" + fieldOutput + "\" не найдено");
                }
                outputFieldsToString.add(fieldOutput + " = " + value);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalArgumentException(e);
            }
        }
        System.out.println("\nOutput fields: " + outputFieldsToString);
    }

}
