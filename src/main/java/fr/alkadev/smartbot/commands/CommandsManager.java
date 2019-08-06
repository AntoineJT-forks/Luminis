package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.polls.commands.PollCommand;
import fr.alkadev.smartbot.system.commands.AboutCommand;
import fr.alkadev.smartbot.system.commands.HelpCommand;
import fr.alkadev.smartbot.reminds.RemindCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CommandsManager {

    private List<CommandRestricted> commands;

    CommandsManager() {
        commands = new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new PollCommand()
        ));
        this.commands.add(new RemindCommand());
        this.commands.add(new HelpCommand(this.commands));
    }

    Optional<CommandRestricted> getCommandExecutorByName(String commandName) {
        return this.commands
                .stream()
                .filter(commandExecutor -> commandExecutor.getCommand().equalsIgnoreCase(commandName))
                .findAny();
    }

}