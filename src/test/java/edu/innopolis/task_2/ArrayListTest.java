package edu.innopolis.task_2;

import org.junit.jupiter.api.Assertions;
import java.lang.reflect.Field;


class ArrayListTest {

    @org.junit.jupiter.api.Test
    void givenArrayList_whenNewElement_thenAddElement() {
        String testString = "New element String type of ArrayList";
        List<String> stringList = new ArrayList<>();
        stringList.add(testString);
        Assertions.assertEquals(testString, stringList.get(0));

        Integer testInteger = 25;
        List<Integer> integerList = new ArrayList<>();
        integerList.add(testInteger);
        Assertions.assertEquals(testInteger, integerList.get(0));
    }

    @org.junit.jupiter.api.Test
    void givenNewElement_whenNotEnoughArrayListSize_thenIncreaseArrayListSize()  {
        int initialArrayListSize = 2;
        int quantityTestElements = 11;
        int exceptedArrayListSize;


        List<Integer> integerList = new ArrayList<>(initialArrayListSize);
        for (int i = 0; i < quantityTestElements; i++) {
            integerList.add((Integer) i);
        }

        try {
            Field fieldInitialArrayListSize = integerList.getClass().getDeclaredField("initialArrayLength");
            fieldInitialArrayListSize.setAccessible(true);
            exceptedArrayListSize =  fieldInitialArrayListSize.getInt(integerList);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            exceptedArrayListSize = initialArrayListSize;
            e.printStackTrace();
        }

        while(exceptedArrayListSize < quantityTestElements) {
            exceptedArrayListSize = (int) (exceptedArrayListSize * ArrayList.ARRAY_MAGNIFICATION_FACTOR);
        }

        Assertions.assertEquals(exceptedArrayListSize, integerList.size());
    }


}