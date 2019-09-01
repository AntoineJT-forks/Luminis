package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.commandsmanagers.CommandsManager;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;

public class SmartBotListenerBuilder {

    public DatabaseManager databaseManager;
    public SmartBotManagers smartBotManagers;
    public CommandsManager commandsManager;

    private SmartBotListenerBuilder() {}

    public static SmartBotListenerBuilder aSmartBotListener() {
        return new SmartBotListenerBuilder();
    }

    public SmartBotListenerBuilder withDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        return this;
    }

    public SmartBotListenerBuilder withSmartBotManagers(SmartBotManagers smartBotManagers) {
        this.smartBotManagers = smartBotManagers;
        return this;
    }

    public SmartBotListenerBuilder withCommandsManager(CommandsManager commandsManager) {
        this.commandsManager = commandsManager;
        return this;
    }

    public SmartBotListener build() {
        return new SmartBotListener(this);
    }

}
