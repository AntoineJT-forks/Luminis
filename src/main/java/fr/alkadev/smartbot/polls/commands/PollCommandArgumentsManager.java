package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.polls.commands.arguments.*;
import fr.alkadev.smartbot.system.managers.SmartBotManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PollCommandArgumentsManager {

    private final List<PollCommandArgument> pollCommandArguments;

    PollCommandArgumentsManager(SmartBotManager pollsManager, SmartBotManager channelsIdsManager, SmartBotManager guildsIdsManager) {
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(),
                new StartArgument(pollsManager),
                new StopArgument(pollsManager),
                new ColorArgument(pollsManager),
                new AskArgument(pollsManager),
                new ChoiceArgument(pollsManager),
                new EmoteArgument(pollsManager),
                new ChannelArgument(channelsIdsManager, guildsIdsManager)
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
