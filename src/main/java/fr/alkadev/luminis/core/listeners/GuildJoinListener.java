package fr.alkadev.luminis.core.listeners;

import fr.alkadev.luminis.core.managers.ChannelsIdsManager;
import fr.alkadev.luminis.core.managers.GuildsIdsManager;
import fr.alkadev.luminis.core.managers.LuminisManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import fr.alkadev.luminis.core.model.GuildChannelsIds;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoinListener extends ListenerAdapter {

    private final LuminisManager<Integer, Long> guildsIdsManager;
    private final LuminisManager<GuildChannelsIds, Integer> channelsIdsManager;

    public GuildJoinListener(LuminisManagers luminisManagers) {
        this.guildsIdsManager = luminisManagers.getManager(GuildsIdsManager.class);
        this.channelsIdsManager = luminisManagers.getManager(ChannelsIdsManager.class);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        long guildId = event.getGuild().getIdLong();

        this.guildsIdsManager.add(guildId, 0);
        this.channelsIdsManager.add(this.guildsIdsManager.get(guildId), new GuildChannelsIds());

        this.channelsIdsManager.get(this.guildsIdsManager.get(guildId)).put("default", event.getGuild().getSystemChannel().getIdLong());
    }

}
