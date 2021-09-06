package edu.innopolis.task_2;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private static final double ARRAY_MAGNIFICATION_FACTOR = 1.5;
    private int initialArrayLength;
    private int currentArrayLength;
    private int currentArrayIndex;

    private E array[];

    public ArrayList(int initialArrayLength) {
        this.initialArrayLength = initialArrayLength;
        initArrayList();
    }

    public ArrayList() {
        this.initialArrayLength = 5;
        initArrayList();
    }

    private void initArrayList () {
        this.array = (E []) new Object[initialArrayLength];
        currentArrayLength = initialArrayLength;
        currentArrayIndex = 0;
    }


    @Override
    public void add(E element) {

        if(currentArrayIndex == currentArrayLength) {
            currentArrayLength *= ARRAY_MAGNIFICATION_FACTOR;
            E tempArray[] = array.clone();
            array = (E []) new Object[currentArrayLength];
            System.arraycopy(tempArray,0, array, 0, tempArray.length);

        }

        array[currentArrayIndex] = element;
        currentArrayIndex++;
    }

    @Override
    public E get(int index) {
        if(index >= 0 && index < currentArrayLength) {
            return array[index];
        } else {
            System.out.print("Index " + index + " is out of bound: ");
            return null;
        }
    }

    private class LinkedListIterator implements Iterator<E>{
        private int currentIteratorIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIteratorIndex < currentArrayIndex;
        }

        @Override
        public E next() {
            int currentIndex = currentIteratorIndex;
            currentIteratorIndex++;
            return array[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }


    @Override
    public String toString() {
        return "ArrayList{" + Arrays.toString(array) + "}";
    }
}
