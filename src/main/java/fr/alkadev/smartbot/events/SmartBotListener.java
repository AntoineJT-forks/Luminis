package fr.alkadev.smartbot.events;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.EventListener;

public class SmartBotListener implements EventListener {

    private final ListenersManager listenersManager;

    public SmartBotListener(char prefix) {
        this.listenersManager = new ListenersManager(prefix);
    }

    @SuppressWarnings("unchecked call")
    @Override
    public void onEvent(Event event) {

        this.listenersManager.getListenersByClass(event.getClass())
                .forEach(listener -> listener.executeListener(event));

    }

}
