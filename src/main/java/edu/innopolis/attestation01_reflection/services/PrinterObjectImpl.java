package edu.innopolis.attestation01_reflection.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Set;

public class PrinterObjectImpl implements Printer {
    @Override
    public <T> void printFields(T object, Set<String> outputFields) {
        ArrayList<String> outputFieldsToString = new ArrayList<>();

        for(String fieldOutput : outputFields) {
            try {
                Field field = object.getClass().getDeclaredField(fieldOutput);
                field.setAccessible(true);
                //Почему-то в задании было написано:
                // "Значения полей, перечисленных в fieldsToOutput, сконвертировать в строку
                // (вызвав toString у объектов или String.valueOf для примитивных типов)"
                //
                // Хотя, прекрасно работает и вот так:
                outputFieldsToString.add(field.getName() + " = " + field.get(object));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }
        System.out.println("\nOutput fields: " + outputFieldsToString);

    }
}
