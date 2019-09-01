package fr.alkadev.luminis.events;

import fr.alkadev.luminis.commands.commandsmanagers.CommandsManager;
import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.system.managers.LuminisManagers;

public class LuminisListenerBuilder {

    public DatabaseManager databaseManager;
    public LuminisManagers luminisManagers;
    public CommandsManager commandsManager;

    private LuminisListenerBuilder() {}

    public static LuminisListenerBuilder aSmartBotListener() {
        return new LuminisListenerBuilder();
    }

    public LuminisListenerBuilder withDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        return this;
    }

    public LuminisListenerBuilder withSmartBotManagers(LuminisManagers luminisManagers) {
        this.luminisManagers = luminisManagers;
        return this;
    }

    public LuminisListenerBuilder withCommandsManager(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
        return this;
    }

    public LuminisListener build() {
        return new LuminisListener(this);
    }

}
