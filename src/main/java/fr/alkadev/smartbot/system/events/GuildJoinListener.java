package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.system.model.GuildChannelsIds;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildJoinListener extends ListenerAdapter {

    private final SmartBotManager<Integer, Long> guildsIdsManager;
    private final SmartBotManager<GuildChannelsIds, Integer> channelsIdsManager;

    public GuildJoinListener(SmartBotManagers smartBotManagers) {
        this.guildsIdsManager = smartBotManagers.getManager(GuildsIdsManager.class);
        this.channelsIdsManager = smartBotManagers.getManager(ChannelsIdsManager.class);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        long guildId = event.getGuild().getIdLong();

        this.guildsIdsManager.add(guildId, 0);
        this.channelsIdsManager.add(this.guildsIdsManager.get(guildId), new GuildChannelsIds());

        this.channelsIdsManager.get(this.guildsIdsManager.get(guildId)).put("default", event.getGuild().getSystemChannel().getIdLong());
    }

}
