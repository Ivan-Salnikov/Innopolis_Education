package edu.innopolis.attestation01_reflection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AttestationReflectionTest {

    @Test
    void cleanUp_ObjectNotExtendsMap() {
        final String testString = "Test string";
        final double testDouble = 25_000d;
        ArrayList<String> listString = new ArrayList<>();
        listString.add("String_1");
        listString.add("String_2");
        Map<String, Integer> mapStringInteger = new HashMap<>();
        mapStringInteger.put("Some_String_1", 279);
        mapStringInteger.put("Some_String_2", 0);


        TestObject testObject = new TestObject(25, listString, mapStringInteger, testString,
                100_000L, (byte) 1, testDouble, 123456789, true,
                12345.569f, 'A', (short) 123);

        Set<String> fieldsToCleanUp = new HashSet<>();
        fieldsToCleanUp.add("objectInteger");
        //fieldsToCleanUp.add("objectArrayList");
        fieldsToCleanUp.add("objectMap");
        //fieldsToCleanUp.add("someString");
        fieldsToCleanUp.add("primitiveLong");
        fieldsToCleanUp.add("primitiveByte");
        //fieldsToCleanUp.add("primitiveDouble");
        fieldsToCleanUp.add("primitiveInt");
        fieldsToCleanUp.add("primitiveBoolean");
        fieldsToCleanUp.add("primitiveFloat");
        fieldsToCleanUp.add("primitiveChar");
        fieldsToCleanUp.add("primitiveShort");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("objectArrayList");
        fieldsToOutput.add("someString");
        fieldsToOutput.add("primitiveDouble");

//        System.out.println("Объект " + testObject);
//        System.out.println("  - список полей для очистки " + fieldsToCleanUp);
//        System.out.println("  - список полей для вывода значений " + fieldsToOutput);

        AttestationReflection.cleanUp(testObject, fieldsToCleanUp, fieldsToOutput);

        Assertions.assertEquals(testObject.getObjectArrayList(), listString);
        Assertions.assertEquals(testObject.getSomeString(), testString);
        Assertions.assertEquals(testObject.getPrimitiveDouble(), testDouble);

        Assertions.assertNull(testObject.getObjectInteger());
        Assertions.assertNull(testObject.getObjectMap());
        Assertions.assertEquals(testObject.getPrimitiveLong(), 0L);
        Assertions.assertEquals(testObject.getPrimitiveByte(), 0);
        Assertions.assertEquals(testObject.getPrimitiveInt(), 0);
        Assertions.assertFalse(testObject.getPrimitiveBoolean());
        Assertions.assertEquals(testObject.getPrimitiveFloat(), 0f);
        Assertions.assertEquals(testObject.getPrimitiveChar(), '\u0000');
        Assertions.assertEquals(testObject.getPrimitiveShort(), 0);

    }


    @Test
    void cleanUp_Object_ExtendsMap() {
        Map<String, Integer> mapStringInteger = new HashMap<>();
        mapStringInteger.put("Mihail", 185);
        mapStringInteger.put("Alexandr", 175);
        mapStringInteger.put("Albert", 182);
        mapStringInteger.put("Marat", 184);
        mapStringInteger.put("Marsel", 169);
        mapStringInteger.put("Ivan", 192);

        Set<String> fieldsToCleanUp = new HashSet<>();
        fieldsToCleanUp.add("Marat");
        fieldsToCleanUp.add("Marsel");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("Ivan");
        fieldsToOutput.add("Alexandr");
        fieldsToOutput.add("Albert");

        AttestationReflection.cleanUp(mapStringInteger, fieldsToCleanUp, fieldsToOutput);

    }
}