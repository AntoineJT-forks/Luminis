package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.MessageReceivedListener;
import fr.alkadev.smartbot.commands.commandsmanagers.CommandsManager;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.system.events.GuildJoinListener;
import fr.alkadev.smartbot.system.events.ReadyListener;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class SmartBotListeners extends ListenersManager {

    public SmartBotListeners(DatabaseManager databaseManager, SmartBotManagers smartBotManagers, CommandsManager commandsManager) {
        super(databaseManager, smartBotManagers, commandsManager);
    }

    @Override
    protected List<ListenerAdapter> getListeners(DatabaseManager databaseManager, SmartBotManagers smartBotManagers, CommandsManager commandsManager) {

        return Arrays.asList(
                new ReadyListener(databaseManager),
                new MessageReceivedListener(commandsManager),
                new GuildJoinListener(smartBotManagers)
        );

    }
}
