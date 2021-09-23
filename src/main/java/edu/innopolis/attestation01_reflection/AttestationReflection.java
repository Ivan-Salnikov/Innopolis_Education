package edu.innopolis.attestation01_reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public  class AttestationReflection {
    private static final Map<Class<?>, Object> DEFAULT_VALUES = Stream
            .of(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)
            .collect(toMap(clazz -> (Class<?>) clazz, clazz -> Array.get(Array.newInstance(clazz, 1), 0)));
    private static  final Object OBJECT_DEFAULT_VALUE = null;

    public static <T> T GetDefaultValueForClass(Class<T> clazz) {
        return (T) DEFAULT_VALUES.get(clazz);
    }

    public static void cleanUp(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        System.out.println(object);
        System.out.println("");

        Field[] fields = object.getClass().getDeclaredFields();

        for( Field field : fields) {
            if(!fieldsToCleanup.contains(field.getName())) continue;

            System.out.print("Field name : " + field.getName());

            field.setAccessible(true);
            try {
                Class<?> fld = field.getType();
                System.out.print(", old value = " + field.get(object));

                if(fld.isPrimitive()) {
                    System.out.print(", primitive type");
                    System.out.print("\nDefault value for \"" + fld + "\" type is: " + GetDefaultValueForClass(fld));
                    field.set(object, GetDefaultValueForClass(fld));
                    }

                 else {
                    System.out.print(", field type : " + fld.getName());
                    field.set(object, OBJECT_DEFAULT_VALUE);
                }

                System.out.println("\nnew value = " + field.get(object));
                System.out.println("");

                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
            }

        }

}
