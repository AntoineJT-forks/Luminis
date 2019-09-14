package fr.alkadev.luminis.core.listeners;

import fr.alkadev.luminis.core.database.DatabaseManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;
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
