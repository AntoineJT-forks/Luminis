package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.events.Listener;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MessageReceivedListener implements Listener<MessageReceivedEvent> {

    private final CommandExecutor commandExecutor;

    public MessageReceivedListener(char prefix, SmartBotManagers smartBotManagers) {
        this.commandExecutor = new CommandExecutor(prefix, smartBotManagers);
    }

    @Override
    public boolean isSameEvent(Class<? extends Event> eventClass) {
        return MessageReceivedEvent.class.equals(eventClass);
    }

    @Override
    public void executeListener(MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            this.commandExecutor.executeCommand(event.getMessage());
        }

    }

}
