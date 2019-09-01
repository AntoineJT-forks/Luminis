package fr.alkadev.luminis.events;

import fr.alkadev.luminis.commands.MessageReceivedListener;
import fr.alkadev.luminis.commands.commandsmanagers.CommandsManager;
import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.system.events.GuildJoinListener;
import fr.alkadev.luminis.system.events.ReadyListener;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

class LuminisListener extends ListenerManager {

    private DatabaseManager databaseManager;
    private LuminisManagers luminisManagers;
    private CommandsManager commandsManager;

    LuminisListener(LuminisListenerBuilder builder) {
        this.databaseManager = builder.databaseManager;
        this.luminisManagers = builder.luminisManagers;
        this.commandsManager = builder.commandsManager;
    }

    @Override
    List<ListenerAdapter> getListeners() {

        return Arrays.asList(
                new ReadyListener(this.databaseManager),
                new MessageReceivedListener(this.commandsManager),
                new GuildJoinListener(this.luminisManagers)
        );

    }

}
