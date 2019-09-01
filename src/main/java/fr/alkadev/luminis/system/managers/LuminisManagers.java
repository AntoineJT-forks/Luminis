package fr.alkadev.luminis.system.managers;

import fr.alkadev.luminis.database.DatabaseSaver;
import fr.alkadev.luminis.polls.PollsManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LuminisManagers {

    private final List<LuminisManager> savers;

    public LuminisManagers() {
        this.savers = Arrays.asList(
                new GuildsIdsManager(),
                new ChannelsIdsManager(),
                new PollsManager()
        );
    }

    public <T, U> LuminisManager<T, U> getManager(Class<? extends LuminisManager<T, U>> managerClass) {
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
