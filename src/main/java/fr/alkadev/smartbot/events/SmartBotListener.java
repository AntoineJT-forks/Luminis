package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.CommandsManager;
import fr.alkadev.smartbot.commands.MessageReceivedListener;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.hooks.EventListener;

import java.util.Arrays;
import java.util.List;

public class SmartBotListener implements EventListener {

    private final List<Listener> events;

    public SmartBotListener(CommandsManager commandManager) {
        events = Arrays.asList(
                new ReadyListener(),
                new MessageReceivedListener(commandManager)
        );
    }

    @Override
    public void onEvent(Event event) {

        events.stream()
                .filter(listener -> listener.getEventClass().equals(event.getClass()))
                .forEach(listener -> listener.executeListener(event));

    }

}
