package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Optional;

class CommandExecutor {

    private CommandsManager commandManager;
    private char prefix;

    CommandExecutor(char prefix) {
        this.commandManager = new CommandsManager(prefix);
        this.prefix = prefix;
    }

    void executeCommand(Message message) {

        String content = message.getContentRaw();

        if (content.length() == 0 || content.charAt(0) != this.prefix) return;

        String[] args = content.substring(1).split(" +");

        Optional<CommandRestricted> optionalAccessRestricted = this.commandManager.getCommandExecutorByName(args[0]);

        if (optionalAccessRestricted.isPresent()) {

            CommandRestricted commandRestricted = optionalAccessRestricted.get();

            if (canExecute(commandRestricted, message)) {
                commandRestricted.execute(message, Arrays.copyOfRange(args, 1, args.length));
            } else {
                message.getChannel().sendMessage("Mauvais channel ou permission manquante.").queue();
            }

        } /* else {

            CustomCommandData customCommandData = new CustomCommandDAO(this.databaseConnection).get(args[0]);

            if (customCommandData != null) {
                message.getChannel().sendMessage(customCommandData.text).queue();
            }

        } */

    }

    private boolean canExecute(CommandRestricted commandExecutor, Message message) {
        return commandExecutor.isAuthorizedChannel(message.getChannel())
                && (message.getChannelType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE || commandExecutor.isAuthorizedMember(message.getMember()));
    }

}
