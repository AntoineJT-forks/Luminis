package fr.alkadev.luminis.events;

import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.system.listeners.GuildJoinListener;
import fr.alkadev.luminis.system.listeners.ReadyListener;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

class LuminisListener extends ListenerManager {

    private DatabaseManager databaseManager;
    private LuminisManagers luminisManagers;

    LuminisListener(LuminisListenerBuilder builder) {
        this.databaseManager = builder.databaseManager;
        this.luminisManagers = builder.luminisManagers;
    }

    @Override
    List<ListenerAdapter> getListeners() {

        return Arrays.asList(
                new ReadyListener(this.databaseManager),
                new GuildJoinListener(this.luminisManagers)
        );

    }

}
