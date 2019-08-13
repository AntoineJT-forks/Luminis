package fr.alkadev.smartbot.system.managers;

import fr.alkadev.smartbot.database.DatabaseSaver;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GuildsIdsManager implements SmartBotManager<Integer, Long>, DatabaseSaver {

    private final Map<Long, Integer> guildsIds;
    private int idIndex = 0;

    GuildsIdsManager() {
        guildsIds = new HashMap<>();
    }

    @Override
    public boolean isPresent(Long guildId) {
        return this.guildsIds.containsKey(guildId);
    }

    public Optional<Integer> get(Long guildId) {
        return Optional.ofNullable(this.guildsIds.get(guildId));
    }

    public void add(Long guildId) {
        this.guildsIds.put(guildId, this.idIndex++);
    }

    public void remove(Long guildId) {
        this.guildsIds.remove(guildId);
    }

    @Override
    public void save(Connection connection) {

    }

    @Override
    public void load(Connection connection) {

    }

}
