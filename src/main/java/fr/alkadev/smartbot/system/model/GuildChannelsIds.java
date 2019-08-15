package fr.alkadev.smartbot.system.model;

import java.util.HashMap;
import java.util.Map;

public class GuildChannelsIds {

    private final Map<String, Long> channelsIds = new HashMap<>();

    public void put(String channelName, long channelId) {
        this.channelsIds.put(channelName, channelId);
    }

    public Map<String, Long> getChannelsIds() {
        return this.channelsIds;
    }

}
