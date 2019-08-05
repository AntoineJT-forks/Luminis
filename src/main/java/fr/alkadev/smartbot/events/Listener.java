package fr.alkadev.smartbot.events;

import net.dv8tion.jda.core.events.Event;

public interface Listener<T extends Event> {

    Class<T> getEventClass();

    void executeListener(T event);

}
