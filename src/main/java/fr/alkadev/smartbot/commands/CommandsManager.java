package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandsManager {

    private final char prefix;
    private List<CommandExecutor> commandExecutors;

    public CommandsManager(char prefix) {
        this.prefix = prefix;
        commandExecutors = new ArrayList<>(Arrays.asList(
                new AboutCommand()
        ));
        this.commandExecutors.add(new HelpCommand(this.commandExecutors));
    }

    void executeCommand(Message message) {

        String content = message.getContentRaw();

        if (content.length() == 0 || content.charAt(0) != this.prefix) return;

        String[] args = content.substring(1).split(" +");

        Optional<CommandExecutor> optionalCommandExecutor = this.commandExecutors.stream()
                .filter(commandExecutor -> commandExecutor.getCommand().equalsIgnoreCase(args[0]))
                .findAny();

        if (optionalCommandExecutor.isPresent()) {

            CommandExecutor commandExecutor = optionalCommandExecutor.get();

            if (canExecute(commandExecutor, message)) {
                commandExecutor.execute(message, Arrays.copyOfRange(args, 1, args.length));
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

    private boolean canExecute(CommandExecutor commandExecutor, Message message) {
        return commandExecutor.getChannelType().isAuthorizedChannel(commandExecutor, message.getChannel())
                && (message.getChannelType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE || commandExecutor.isAuthorizedMember(message.getMember()));
    }

}