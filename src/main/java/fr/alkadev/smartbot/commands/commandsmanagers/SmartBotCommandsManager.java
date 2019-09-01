package fr.alkadev.smartbot.commands.commandsmanagers;


import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.commands.PollCommand;
import fr.alkadev.smartbot.system.commands.AboutCommand;
import fr.alkadev.smartbot.system.commands.HelpCommand;
import fr.alkadev.smartbot.system.commands.RemindCommand;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartBotCommandsManager extends CommandsManager {

    private final SmartBotManagers smartBotManagers;
    private final List<CommandRestricted> commands;

    public SmartBotCommandsManager(SmartBotManagers smartBotManagers) {
        this.smartBotManagers = smartBotManagers;
        this.commands = this.getCommandsList();
    }

    private List<CommandRestricted> getCommandsList() {

        List<CommandRestricted> commands = new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new RemindCommand(),
                new PollCommand(smartBotManagers)
        ));

        commands.add(new HelpCommand(commands));

        return commands;
    }

    @Override
    protected List<CommandRestricted> getCommands() {
        return this.commands;
    }

}
