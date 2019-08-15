package fr.alkadev.smartbot.system.managers;

public interface SmartBotManager<T, U> {

    boolean isPresent(U index);

    T get(U index);

    void add(U index, T element);

    void remove(U index);

}
