package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.commands.commandsmanagers.CommandsManager;
import fr.alkadev.smartbot.events.Listener;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageReceivedListener implements Listener<MessageReceivedEvent> {

    private final CommandsManager commandsManager;

    public MessageReceivedListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    @Override
    public boolean isSameEvent(Class<? extends Event> eventClass) {
        return MessageReceivedEvent.class.equals(eventClass);
    }

    @Override
    public void executeListener(MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            this.commandsManager.executeCommand(event.getMessage());
        }

    }

}
