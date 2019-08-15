package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommand;
import fr.alkadev.smartbot.system.commands.RemindCommand;
import fr.alkadev.smartbot.system.commands.AboutCommand;
import fr.alkadev.smartbot.system.commands.HelpCommand;
import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CommandsManager {

    private List<CommandRestricted> commands;

    CommandsManager(SmartBotManagers smartBotManagers) {
        commands = new ArrayList<>(Arrays.asList(
                new AboutCommand(),
                new RemindCommand(),
                new PollCommand(smartBotManagers.getManager(PollsManager.class), smartBotManagers.getManager(ChannelsIdsManager.class), smartBotManagers.getManager(GuildsIdsManager.class))
        ));
        this.commands.add(new HelpCommand(this.commands));
    }

    Optional<CommandRestricted> getCommandExecutorByName(String commandName) {
        return this.commands
                .stream()
                .filter(commandExecutor -> commandExecutor.getCommand().equalsIgnoreCase(commandName))
                .findAny();
    }

}