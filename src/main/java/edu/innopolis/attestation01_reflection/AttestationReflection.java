package edu.innopolis.attestation01_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

public class AttestationReflection {

    public static void cleanUp(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        Field[] fields = object.getClass().getDeclaredFields();

        for( Field field : fields) {
            Type t = typeof(field.getType());

            System.out.println(field.getName());
            typeField =
            System.out.println(field.getType());
            System.out.println(field.getType().isInstance(Object.class));
        }

    }
}
