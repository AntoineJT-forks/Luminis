package fr.alkadev.luminis.commands.commandsmanagers;


import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.polls.commands.PollCommand;
import fr.alkadev.luminis.system.commands.AboutCommand;
import fr.alkadev.luminis.system.commands.HelpCommand;
import fr.alkadev.luminis.system.commands.RemindCommand;
import fr.alkadev.luminis.system.managers.LuminisManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuminisCommandsManager extends CommandsManager {

    private final LuminisManagers luminisManagers;
    private final List<CommandRestricted> commands;

    public LuminisCommandsManager(LuminisManagers luminisManagers) {
        this.luminisManagers = luminisManagers;
        this.commands = this.getCommandsList();
    }

    private List<CommandRestricted> getCommandsList() {

        List<CommandRestricted> commands = new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new RemindCommand(),
                new PollCommand(luminisManagers)
        ));

        commands.add(new HelpCommand(commands));

        return commands;
    }

    @Override
    protected List<CommandRestricted> getCommands() {
        return this.commands;
    }

}
