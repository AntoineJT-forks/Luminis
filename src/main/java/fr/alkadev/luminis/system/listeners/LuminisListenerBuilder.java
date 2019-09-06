package fr.alkadev.luminis.system.listeners;

import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.system.managers.LuminisManagers;

public class LuminisListenerBuilder {

    DatabaseManager databaseManager;
    LuminisManagers luminisManagers;

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

    public LuminisListener build() {
        return new LuminisListener(this);
    }

}
