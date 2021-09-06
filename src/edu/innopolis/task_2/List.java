package edu.innopolis.task_2;

public interface List <E>{
    void add (E element);
    E get (int index);
    Iterator<E> iterator ();
}
