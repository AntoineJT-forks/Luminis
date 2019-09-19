package fr.alkadev.luminis.addons.system.listeners;

import fr.alkadev.luminis.core.managers.ChannelsIdsManager;
import fr.alkadev.luminis.core.managers.GuildsIdsManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import fr.alkadev.luminis.core.model.GuildChannelsIds;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildJoinListener extends ListenerAdapter {

    private final GuildsIdsManager guildsIdsManager;
    private final ChannelsIdsManager channelsIdsManager;

    public GuildJoinListener(LuminisManagers luminisManagers) {
        this.guildsIdsManager = (GuildsIdsManager) luminisManagers.getManager(GuildsIdsManager.class);
        this.channelsIdsManager = (ChannelsIdsManager) luminisManagers.getManager(ChannelsIdsManager.class);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        long guildId = event.getGuild().getIdLong();

        this.guildsIdsManager.add(guildId, 0);

        Integer luminisGuildId = this.guildsIdsManager.get(guildId);
        this.channelsIdsManager.add(luminisGuildId, new GuildChannelsIds());
        this.channelsIdsManager.get(luminisGuildId).put("default", 0L);
    }

}
