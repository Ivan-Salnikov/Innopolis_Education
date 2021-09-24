package edu.innopolis.attestation01_reflection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class ExecutorServiceMapImpl implements ExecutorService {
    @Override
    public void validateFields(Object object, Set<String> allFields) {
        for(String field : allFields){
            try {
                if(object.getClass().getDeclaredMethod("get", Object.class).invoke(object, field) == null) {
                    throw new IllegalArgumentException("Поле \"" + field + "\" не найдено");
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Override
    public void cleanFields(Object object, Set<String> cleanFields) {
        for(String field : cleanFields){
            try {
                if(object.getClass().getDeclaredMethod("remove", Object.class).invoke(object, field) == null) {
                    throw new IllegalArgumentException("Поле \"" + field + "\" не найдено");
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
