package fr.alkadev.luminis.commands.commandsmanagers;

import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class CommandsManager {

    public void executeCommand(Message message) {

        String content = message.getContentRaw();

        if (content.length() == 0 || content.charAt(0) != '*') return;

        String[] args = content.substring(1).split(" +");

        Optional<CommandRestricted> optionalAccessRestricted = this.getCommands()
                .stream()
                .filter(commandRestricted -> commandRestricted.getCommand().equalsIgnoreCase(args[0]))
                .findAny();

        if (optionalAccessRestricted.isPresent()) {
            this.executeCommand(message, args, optionalAccessRestricted.get());
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "<*help> pour obtenir la liste des commandes disponibles.");
    }

    private void executeCommand(Message message, String[] args, CommandRestricted commandRestricted) {

        if (this.canExecute(commandRestricted, message)) {
            commandRestricted.execute(message, Arrays.copyOfRange(args, 1, args.length));
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "Mauvais channel ou permission manquante.");
    }

    private boolean canExecute(CommandRestricted commandExecutor, Message message) {
        return commandExecutor.isAuthorizedChannel(message.getChannel())
                && (message.getChannelType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE
                || commandExecutor.isAuthorizedMember(message.getMember()));
    }

    protected abstract List<CommandRestricted> getCommands();

}