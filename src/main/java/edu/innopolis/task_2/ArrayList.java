package edu.innopolis.task_2;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    public static final double ARRAY_MAGNIFICATION_FACTOR = 1.5;
    private static final int DEFAULT_ARRAY_SIZE = 5;
    private int initialArrayLength;
    private int currentArrayLength;
    private int currentArrayIndex;

    private T[] array;

    public ArrayList(int initialArrayLength) {
        initialArrayLength = initialArrayLength < 5 ? DEFAULT_ARRAY_SIZE : initialArrayLength;
        this.initialArrayLength = initialArrayLength;
        this.array = (T []) new Object[initialArrayLength];
        currentArrayLength = initialArrayLength;
        currentArrayIndex = 0;
    }

    public ArrayList() {
        this(DEFAULT_ARRAY_SIZE);
    }


    @Override
    public void add(T element) {

        if(currentArrayIndex == currentArrayLength) {
            currentArrayLength *= ARRAY_MAGNIFICATION_FACTOR;
            T[] tempArray = array.clone();
            array = (T []) new Object[currentArrayLength];
            System.arraycopy(tempArray,0, array, 0, tempArray.length);

        }

        array[currentArrayIndex] = element;
        currentArrayIndex++;
    }

    @Override
    public T get(int index) {
        if(index >= 0 && index < currentArrayLength) {
            return array[index];
        } else {
            System.out.print("Index " + index + " is out of bound: ");
            return null;
        }
    }
    @Override
    public int size() {
        return currentArrayLength;
    }

    private class ArrayListIterator implements Iterator<T>{
        private int currentIteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIteratorIndex < currentArrayIndex;
        }

        @Override
        public T next() {
            int currentIndex = currentIteratorIndex;
            currentIteratorIndex++;
            return array[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    @Override
    public String toString() {
        return "ArrayList{" + Arrays.toString(array) + "}";
    }
}
