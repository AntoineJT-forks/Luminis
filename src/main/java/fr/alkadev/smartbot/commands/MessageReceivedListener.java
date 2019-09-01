package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.commands.commandsmanagers.CommandsManager;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {

    private final CommandsManager commandsManager;

    public MessageReceivedListener(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getAuthor().isBot()) {
            this.commandsManager.executeCommand(event.getMessage());
        }

    }

}
