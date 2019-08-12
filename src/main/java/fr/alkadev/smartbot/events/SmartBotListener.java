package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.database.DatabaseConnection;
import fr.alkadev.smartbot.database.DatabaseManager;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.EventListener;

public class SmartBotListener implements EventListener {

    private final ListenersManager listenersManager;

    public SmartBotListener(char prefix, DatabaseManager databaseManager) {
        this.listenersManager = new ListenersManager(prefix, databaseManager);
    }

    @SuppressWarnings("unchecked call")
    @Override
    public void onEvent(Event event) {

        this.listenersManager.getListenersByClass(event.getClass())
                .forEach(listener -> listener.executeListener(event));

    }

}
