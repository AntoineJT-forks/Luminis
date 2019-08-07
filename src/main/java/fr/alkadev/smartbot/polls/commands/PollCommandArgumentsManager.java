package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.arguments.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PollCommandArgumentsManager {

    private final List<PollCommandArgument> pollCommandArguments;

    PollCommandArgumentsManager() {
        PollsManager pollsManager = new PollsManager();
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(),
                new StartArgument(pollsManager),
                new StopArgument(pollsManager),
                new ColorArgument(pollsManager)
        ));
        this.pollCommandArguments.add(new HelpArgument(this.pollCommandArguments));
    }

    Optional<PollCommandArgument> getArgumentByName(String argumentName) {
        return this.pollCommandArguments
                .stream()
                .filter(commandRestricted -> commandRestricted.getCommand().equalsIgnoreCase(argumentName))
                .findAny();
    }

}
