package fr.alkadev.luminis.core.managers;

public interface LuminisManager<T, U> {

    boolean isPresent(U index);

    T get(U index);

    void add(U index, T element);

    void remove(U index);

}