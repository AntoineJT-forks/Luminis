package fr.alkadev.smartbot.commands.commandsmanagers;


import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommand;
import fr.alkadev.smartbot.system.commands.AboutCommand;
import fr.alkadev.smartbot.system.commands.HelpCommand;
import fr.alkadev.smartbot.system.commands.RemindCommand;
import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartBotCommandsManager extends CommandsManager {

    public SmartBotCommandsManager(SmartBotManagers smartBotManagers) {
        super(smartBotManagers);
    }

    protected List<CommandRestricted> getCommands(SmartBotManagers smartBotManagers) {

        List<CommandRestricted> commands = new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new RemindCommand(),
                new PollCommand(smartBotManagers.getManager(PollsManager.class), smartBotManagers.getManager(ChannelsIdsManager.class), smartBotManagers.getManager(GuildsIdsManager.class))
        ));

        commands.add(new HelpCommand(commands));

        return commands;
    }

}
