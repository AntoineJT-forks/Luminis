package fr.alkadev.smartbot.system.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChannelsIdsManager implements SmartBotManager<Map<String, Long>, Long> {

    private final Map<Long, Map<String, Long>> channelsIds = new HashMap<>();

    @Override
    public boolean isPresent(Long guildId) {
        return this.channelsIds.containsKey(guildId);
    }

    @Override
    public Optional<Map<String, Long>> get(Long guildId) {
        return Optional.ofNullable(this.channelsIds.get(guildId));
    }

    @Override
    public void add(Long guildId) {
        this.channelsIds.put(guildId, new HashMap<>());
    }

    @Override
    public void remove(Long guildId) {
        this.channelsIds.remove(guildId);
    }

}
