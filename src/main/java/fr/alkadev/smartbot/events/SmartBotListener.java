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

public class SmartBotListener extends ListenerManager {

    private DatabaseManager databaseManager;
    private SmartBotManagers smartBotManagers;
    private CommandsManager commandsManager;

    public SmartBotListener(SmartBotListenerBuilder builder) {
        this.databaseManager = builder.databaseManager;
        this.smartBotManagers = builder.smartBotManagers;
        this.commandsManager = builder.commandsManager;
    }

    @Override
    List<ListenerAdapter> getListeners() {

        return Arrays.asList(
                new ReadyListener(this.databaseManager),
                new MessageReceivedListener(this.commandsManager),
                new GuildJoinListener(this.smartBotManagers)
        );

    }

}
