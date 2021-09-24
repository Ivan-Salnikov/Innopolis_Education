package edu.innopolis.attestation01_reflection.services;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ExecutorServiceObjectImpl implements ExecutorService {
    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));
    private static  final Object OBJECT_DEFAULT_VALUE = null;

    public static <T> T GetDefaultValueForClass(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }

    @Override
    public void validateFields(Object object, Set<String> allFields) {
        for(String field : allFields) {
            try {
                object.getClass().getDeclaredField(field);
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void cleanFields(Object object, Set<String> cleanFields) {
        for(String fieldClean : cleanFields) {
            try {
                Field field = object.getClass().getDeclaredField(fieldClean);
                Class<?> fld = field.getType();
                field.setAccessible(true);
                field.set(object, fld.isPrimitive() ? GetDefaultValueForClass(fld) : OBJECT_DEFAULT_VALUE);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        }

    }
}
