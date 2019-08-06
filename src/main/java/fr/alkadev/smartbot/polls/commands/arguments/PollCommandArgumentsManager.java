package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PollCommandArgumentsManager {

    private final List<CommandRestricted> pollCommandArguments;

    public PollCommandArgumentsManager() {
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(),
                new StartArgument()
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
