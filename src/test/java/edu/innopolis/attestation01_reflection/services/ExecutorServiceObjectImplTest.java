package edu.innopolis.attestation01_reflection.services;

import edu.innopolis.attestation01_reflection.Cleaner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class ExecutorServiceObjectImplTest {
    private static ExecutorService executorService = new ExecutorServiceObjectImpl();

    @Test
    void validateFields_whenAllFieldExists_thenNoneExceptions() {
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
        fieldsToCleanUp.add("objectMap");
        fieldsToCleanUp.add("primitiveLong");
        fieldsToCleanUp.add("primitiveByte");
        fieldsToCleanUp.add("primitiveInt");
        fieldsToCleanUp.add("primitiveBoolean");
        fieldsToCleanUp.add("primitiveFloat");
        fieldsToCleanUp.add("primitiveChar");
        fieldsToCleanUp.add("primitiveShort");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("objectArrayList");
        fieldsToOutput.add("someString");
        fieldsToOutput.add("primitiveDouble");

        executorService.validateFields(testObject, ExecutorService.collectFields(fieldsToCleanUp,fieldsToOutput));
        Assertions.assertTrue(true);
    }
    @Test
    void validateFields_whenAnyFieldNotExists_thenThrowIllegalArgumentException() {
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
        fieldsToCleanUp.add("objectMap");
        fieldsToCleanUp.add("primitiveLong");
        fieldsToCleanUp.add("primitiveByte");
        fieldsToCleanUp.add("primitiveInt");
        fieldsToCleanUp.add("primitiveBoolean");
        fieldsToCleanUp.add("primitiveFloat");
        fieldsToCleanUp.add("primitiveChar");
        fieldsToCleanUp.add("primitiveShort");

        Set<String> fieldsToOutput = new HashSet<>();
        fieldsToOutput.add("objectArrayList");
        fieldsToOutput.add("someString");
        fieldsToOutput.add("primitiveDouble");

        fieldsToOutput.add("Mistake");//java.lang.IllegalArgumentException: Поле "Mistake" не найдено

        Assertions.assertThrows(IllegalArgumentException.class,() -> executorService.validateFields(testObject,
                ExecutorService.collectFields(fieldsToCleanUp, fieldsToOutput)));
    }

    @Test
    void cleanFields_whenAllFieldExists_thenNoneExceptions() {
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
        fieldsToCleanUp.add("objectMap");
        fieldsToCleanUp.add("primitiveLong");
        fieldsToCleanUp.add("primitiveByte");
        fieldsToCleanUp.add("primitiveInt");
        fieldsToCleanUp.add("primitiveBoolean");
        fieldsToCleanUp.add("primitiveFloat");
        fieldsToCleanUp.add("primitiveChar");
        fieldsToCleanUp.add("primitiveShort");

        executorService.cleanFields(testObject, fieldsToCleanUp);

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

}