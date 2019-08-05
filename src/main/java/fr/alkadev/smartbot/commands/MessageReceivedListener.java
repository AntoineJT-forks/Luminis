package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.events.Listener;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageReceivedListener implements Listener<MessageReceivedEvent> {

    private final CommandsManager commandManager;

    public MessageReceivedListener(CommandsManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public Class<MessageReceivedEvent> getEventClass() {
        return MessageReceivedEvent.class;
    }

    @Override
    public void executeListener(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        this.commandManager.executeCommand(event.getMessage());
    }

}
