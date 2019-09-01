package fr.alkadev.luminis.polls.commands;

import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.PollsManager;
import fr.alkadev.luminis.polls.commands.arguments.*;
import fr.alkadev.luminis.system.managers.ChannelsIdsManager;
import fr.alkadev.luminis.system.managers.GuildsIdsManager;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.system.managers.LuminisManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class PollCommandArgumentsManager {

    private final List<PollCommandArgument> pollCommandArguments;

    PollCommandArgumentsManager(LuminisManagers luminisManagers) {

        LuminisManager<PollBuilder, Long> pollsManager = luminisManagers.getManager(PollsManager.class);
        pollCommandArguments = new ArrayList<>(Arrays.asList(
                new EmptyArgument(),
                new StartArgument(pollsManager),
                new StopArgument(pollsManager),
                new ColorArgument(pollsManager),
                new AskArgument(pollsManager),
                new ChoiceArgument(pollsManager),
                new EmoteArgument(pollsManager),
                new ChannelArgument(luminisManagers.getManager(ChannelsIdsManager.class), luminisManagers.getManager(GuildsIdsManager.class))
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
