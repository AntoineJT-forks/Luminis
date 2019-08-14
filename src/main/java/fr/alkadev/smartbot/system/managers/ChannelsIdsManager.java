package fr.alkadev.smartbot.system.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChannelsIdsManager implements SmartBotManager<Map<String, Long>, Integer> {

    private final Map<Integer, Map<String, Long>> channelsIds = new HashMap<>();

    @Override
    public boolean isPresent(Integer guildId) {
        return this.channelsIds.containsKey(guildId);
    }

    @Override
    public Optional<Map<String, Long>> get(Integer guildId) {
        return Optional.ofNullable(this.channelsIds.get(guildId));
    }

    @Override
    public void add(Integer guildId, Map<String, Long> map) {
        this.channelsIds.put(guildId, map);
    }

    @Override
    public void remove(Integer guildId) {
        this.channelsIds.remove(guildId);
    }

}
