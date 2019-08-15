package fr.alkadev.smartbot.events;

import fr.alkadev.smartbot.commands.MessageReceivedListener;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.system.events.GuildJoinListener;
import fr.alkadev.smartbot.system.events.ReadyListener;
import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import net.dv8tion.jda.core.events.Event;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ListenersManager {

    private final List<Listener> listeners;

    public ListenersManager(char prefix, DatabaseManager databaseManager, SmartBotManagers smartBotManagers) {
        this.listeners = Arrays.asList(
                new ReadyListener(databaseManager),
                new MessageReceivedListener(prefix, smartBotManagers),
                new GuildJoinListener(smartBotManagers.getManager(GuildsIdsManager.class), smartBotManagers.getManager(ChannelsIdsManager.class))
        );
    }

    Stream<Listener> getListenersByClass(Class<? extends Event> eventClass) {
        return this.listeners
                .stream()
                .filter(listener -> listener.isSameEvent(eventClass));
    }

}
