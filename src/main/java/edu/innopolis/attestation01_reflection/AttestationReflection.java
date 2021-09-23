package edu.innopolis.attestation01_reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class AttestationReflection {
    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));
    private static  final Object OBJECT_DEFAULT_VALUE = null;

    public static <T> T GetDefaultValueForClass(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }

    public static void cleanUp(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        //checking is field from fieldClean exists in object
        Set<String> allFields = new HashSet<>();
        allFields.addAll(fieldsToCleanup);
        allFields.addAll(fieldsToOutput);

        if (Map.class.isAssignableFrom(object.getClass())) {
            for(String field : allFields){
                try {
                    if(object.getClass().getDeclaredMethod("get", Object.class).invoke(object, field) == null) {
                        throw new IllegalArgumentException("Поле \"" + field + "\" не найдено");
                    }
                    //System.out.println(object.getClass().getDeclaredMethod("get", Object.class).invoke(object, field));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            return;
        }

        for(String field : allFields) {
            try {
                object.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(e);
            }
        }

        for(String fieldClean : fieldsToCleanup) {
            try {
                Field field = object.getClass().getDeclaredField(fieldClean);
                Class<?> fld = field.getType();
                field.setAccessible(true);
                field.set(object, fld.isPrimitive() ? GetDefaultValueForClass(fld) : OBJECT_DEFAULT_VALUE);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }

        ArrayList<String> outputFieldsToString = new ArrayList<>();

        for(String fieldOutput : fieldsToOutput) {
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
