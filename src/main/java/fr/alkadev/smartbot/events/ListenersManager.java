package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.commandsmanagers.CommandsManager;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public abstract class ListenersManager {

    private final List<ListenerAdapter> listenerAdapters;

    public ListenersManager(DatabaseManager databaseManager, SmartBotManagers smartBotManagers, CommandsManager commandsManager) {
        this.listenerAdapters = this.getListeners(databaseManager, smartBotManagers, commandsManager);
    }

    protected abstract List<ListenerAdapter> getListeners(DatabaseManager databaseManager, SmartBotManagers smartBotManagers, CommandsManager commandsManager);

    public void saveListeners(JDA jda) {
        this.listenerAdapters.forEach(jda::addEventListener);
    }

}
