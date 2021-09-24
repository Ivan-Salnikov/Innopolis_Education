package edu.innopolis.attestation01_reflection;

import edu.innopolis.attestation01_reflection.services.*;

import java.util.*;

public class Cleaner {

    public static void cleanUp(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {

        Set<String> allFields = ExecutorService.collectFields(fieldsToCleanup, fieldsToOutput);

        if (!Map.class.isAssignableFrom(object.getClass())) {
            ExecutorService executorService = new ExecutorServiceObjectImpl();
            executorService.validateFields(object, allFields);
            executorService.cleanFields(object, fieldsToCleanup);

            Printer printer = new PrinterObjectImpl();
            printer.printFields(object, fieldsToOutput);
        } else  {
            ExecutorService executorService = new ExecutorServiceMapImpl();
            executorService.validateFields(object, allFields);
            executorService.cleanFields(object, fieldsToCleanup);

            Printer printer = new PrinterMapImpl();
            printer.printFields(object, fieldsToOutput);

        }

    }

}
