package fr.alkadev.smartbot.events;

import net.dv8tion.jda.core.events.Event;

public interface Listener<T extends Event> {

    boolean isSameEvent(Class<? extends Event> eventClass);

    void executeListener(T event);

}
