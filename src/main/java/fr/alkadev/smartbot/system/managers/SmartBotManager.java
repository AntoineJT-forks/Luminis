package fr.alkadev.smartbot.system.managers;

import java.util.Optional;

public interface SmartBotManager<T, U> {

    boolean isPresent(U index);

    Optional<T> get(U index);

    void add(U index, T element);

    void remove(U index);

}
