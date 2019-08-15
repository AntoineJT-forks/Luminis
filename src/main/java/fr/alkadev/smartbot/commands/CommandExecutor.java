package fr.alkadev.smartbot.commands;


import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Optional;

class CommandExecutor {

    private CommandsManager commandManager;
    private char prefix;

    CommandExecutor(char prefix, SmartBotManagers smartBotManagers) {
        this.commandManager = new CommandsManager(smartBotManagers);
        this.prefix = prefix;
    }

    void executeCommand(Message message) {

        String content = message.getContentRaw();

        if (content.length() == 0 || content.charAt(0) != this.prefix) return;

        String[] args = content.substring(1).split(" +");

        Optional<CommandRestricted> optionalAccessRestricted = this.commandManager.getCommandExecutorByName(args[0]);

        if (optionalAccessRestricted.isPresent()) {
            executeCommand(message, args, optionalAccessRestricted.get());
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "<*help> pour obtenir la liste des commandes disponibles.");
    }

    private void executeCommand(Message message, String[] args, CommandRestricted commandRestricted) {

        if (canExecute(commandRestricted, message)) {
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

}
