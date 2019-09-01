package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.polls.PollBuilder;
import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.arguments.*;
import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PollCommandArgumentsManager {

    private final List<PollCommandArgument> pollCommandArguments;

    PollCommandArgumentsManager(SmartBotManagers smartBotManagers) {

        SmartBotManager<PollBuilder, Long> pollsManager = smartBotManagers.getManager(PollsManager.class);
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(),
                new StartArgument(pollsManager),
                new StopArgument(pollsManager),
                new ColorArgument(pollsManager),
                new AskArgument(pollsManager),
                new ChoiceArgument(pollsManager),
                new EmoteArgument(pollsManager),
                new ChannelArgument(smartBotManagers.getManager(ChannelsIdsManager.class), smartBotManagers.getManager(GuildsIdsManager.class))
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
