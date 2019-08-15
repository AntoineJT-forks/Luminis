package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.events.Listener;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.system.model.GuildChannelsIds;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;

public class GuildJoinListener implements Listener<GuildJoinEvent> {

    private final SmartBotManager<Integer, Long> guildsIdsManager;
    private final SmartBotManager<GuildChannelsIds, Integer> channelsIdsManager;

    @SuppressWarnings("unchecked")
    public GuildJoinListener(SmartBotManager guildsIdsManager, SmartBotManager channelsIdsManager) {
        this.guildsIdsManager = guildsIdsManager;
        this.channelsIdsManager = channelsIdsManager;
    }

    @Override
    public boolean isSameEvent(Class<? extends Event> eventClass) {
        return GuildJoinEvent.class.equals(eventClass);
    }

    @Override
    public void executeListener(GuildJoinEvent event) {
        long guildId = event.getGuild().getIdLong();

        this.guildsIdsManager.add(guildId, 0);
        this.channelsIdsManager.add(this.guildsIdsManager.get(guildId), new GuildChannelsIds());

        this.channelsIdsManager.get(this.guildsIdsManager.get(guildId)).put("default", event.getGuild().getSystemChannel().getIdLong());
    }

}
