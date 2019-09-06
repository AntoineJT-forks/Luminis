package fr.alkadev.luminis.commands;


import com.jagrosh.jdautilities.command.Command;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.PollsManager;
import fr.alkadev.luminis.polls.commands.PollCommand;
import fr.alkadev.luminis.polls.commands.arguments.*;
import fr.alkadev.luminis.system.commands.AboutCommand;
import fr.alkadev.luminis.system.commands.RemindCommand;
import fr.alkadev.luminis.system.commands.UserInfosCommand;
import fr.alkadev.luminis.system.managers.ChannelsIdsManager;
import fr.alkadev.luminis.system.managers.GuildsIdsManager;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.system.managers.LuminisManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuminisCommandsManager extends CommandsManager {

    private final LuminisManagers luminisManagers;

    public LuminisCommandsManager(LuminisManagers luminisManagers) {
        this.luminisManagers = luminisManagers;
    }

    @Override
    protected List<Command> getCommands() {
        LuminisManager<PollBuilder, Long> pollsManager = luminisManagers.getManager(PollsManager.class);

        List<Command> pollsArguments = Arrays.asList(
                new StartArgument(pollsManager),
                new CancelArgument(pollsManager),
                new ColorArgument(pollsManager),
                new AskArgument(pollsManager),
                new ChoiceArgument(pollsManager),
                new EmoteArgument(pollsManager),
                new ChannelArgument(luminisManagers.getManager(ChannelsIdsManager.class), luminisManagers.getManager(GuildsIdsManager.class))
        );

        return new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new RemindCommand(),
                new UserInfosCommand(),
                new PollCommand(pollsArguments)
        ));
    }

}
