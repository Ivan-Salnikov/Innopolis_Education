package edu.innopolis.attestation01_reflection.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Set;

public class PrinterMapImpl implements Printer {
    @Override
    public void printFields(Object object, Set<String> outputFields) {

        ArrayList<String> outputFieldsToString = new ArrayList<>();

        for(String fieldOutput : outputFields) {
            try {
                String value = "" +  object.getClass().getDeclaredMethod("get", Object.class).invoke(object, fieldOutput);
                outputFieldsToString.add(fieldOutput + " = " + value);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new IllegalArgumentException(e);
            }
        }
        System.out.println("\nOutput fields: " + outputFieldsToString);
    }
}
