package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PollCommandArgumentsManager {

    private final List<CommandRestricted> pollCommandArguments;

    public PollCommandArgumentsManager(char prefix, PollsManager pollsManager) {
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(prefix),
                new StartArgument(pollsManager),
                new StopCommand(pollsManager)
        ));
        this.pollCommandArguments.add(new HelpArgument(this.pollCommandArguments));
    }

    public Optional<CommandRestricted> getArgumentByName(String argumentName) {
        return this.pollCommandArguments
                .stream()
                .filter(commandRestricted -> commandRestricted.getCommand().equalsIgnoreCase(argumentName))
                .findAny();
    }

}
