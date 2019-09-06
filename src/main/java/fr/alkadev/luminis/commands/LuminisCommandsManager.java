package fr.alkadev.luminis.commands;


import com.jagrosh.jdautilities.command.Command;
import fr.alkadev.luminis.system.commands.AboutCommand;
import fr.alkadev.luminis.system.commands.RemindCommand;
import fr.alkadev.luminis.system.managers.LuminisManagers;

import java.util.Arrays;
import java.util.List;

public class LuminisCommandsManager extends CommandsManager {

    private final LuminisManagers luminisManagers;

    public LuminisCommandsManager(LuminisManagers luminisManagers) {
        this.luminisManagers = luminisManagers;
    }

    @Override
    protected List<Command> getCommands() {
        return Arrays.asList(
                new AboutCommand()
                new RemindCommand(),
//                new PollCommand(luminisManagers),
//                new UserInfosCommand()
        );
    }

}
