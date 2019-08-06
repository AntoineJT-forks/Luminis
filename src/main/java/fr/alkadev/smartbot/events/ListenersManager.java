package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.MessageReceivedListener;
import fr.alkadev.smartbot.system.events.ReadyListener;
import net.dv8tion.jda.core.events.Event;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class ListenersManager {

    private final List<Listener> listeners;

    ListenersManager(char prefix) {
        this.listeners = Arrays.asList(
                new ReadyListener(),
                new MessageReceivedListener(prefix)
        );
    }

    Stream<Listener> getListenersByClass(Class<? extends Event> eventClass) {
        return this.listeners
                .stream()
                .filter(listener -> listener.getEventClass().equals(eventClass));
    }

}
