package fr.alkadev.smartbot.system.managers;

import fr.alkadev.smartbot.database.DatabaseSaver;
import fr.alkadev.smartbot.polls.PollsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SmartBotManagers {

    private final List<SmartBotManager> savers;

    public SmartBotManagers() {
        this.savers = Arrays.asList(
                new GuildsIdsManager(),
                new ChannelsIdsManager(),
                new PollsManager()
        );
    }

    public SmartBotManager getManager(Class<? extends SmartBotManager> managerClass) {
        return this.savers
                .stream()
                .filter(manager -> manager.getClass().equals(managerClass))
                .findAny()
                .orElse(null);
    }

    public List<DatabaseSaver> getSavers() {
        return this.savers
                .stream()
                .filter(manager -> manager instanceof DatabaseSaver)
                .map(manager -> (DatabaseSaver) manager)
                .collect(Collectors.toList());
    }

}
