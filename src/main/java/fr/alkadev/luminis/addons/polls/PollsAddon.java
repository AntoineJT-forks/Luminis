package fr.alkadev.luminis.addons.polls;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.polls.commands.PollCommand;
import fr.alkadev.luminis.addons.polls.commands.arguments.*;
import fr.alkadev.luminis.addons.polls.model.PollsManager;
import fr.alkadev.luminis.core.managers.ChannelsIdsManager;
import fr.alkadev.luminis.core.managers.GuildsIdsManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;

import java.util.Arrays;
import java.util.List;

public class PollsAddon implements Addon {

    private LuminisManagers luminisManagers;
    private PollsManager pollsManager;

    @Override
    public boolean start(LuminisManagers luminisManagers) {

        this.pollsManager = new PollsManager();
        this.luminisManagers = luminisManagers;

        return true;
    }

    @Override
    public void registerCommand(CommandClientBuilder clientBuilder) {

        List<Command> arguments = Arrays.asList(
                new StartArgument(pollsManager),
                new CancelArgument(pollsManager),
                new ColorArgument(pollsManager),
                new AskArgument(pollsManager),
                new ChoiceArgument(pollsManager),
                new EmoteArgument(pollsManager),
                new ChannelArgument(luminisManagers.getManager(ChannelsIdsManager.class), luminisManagers.getManager(GuildsIdsManager.class))
        );

        clientBuilder.addCommand(new PollCommand(arguments));
        arguments.forEach(clientBuilder::addCommand);

    }

    @Override
    public String getName() {
        return "Polls";
    }

}
