package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.events.Listener;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;

public class GuildJoinListener implements Listener<GuildJoinEvent> {

    private final GuildsIdsManager guildsIdsManager;

    public GuildJoinListener(SmartBotManager guildsIdsManager) {
        this.guildsIdsManager = (GuildsIdsManager) guildsIdsManager;
    }

    @Override
    public Class<GuildJoinEvent> getEventClass() {
        return GuildJoinEvent.class;
    }

    @Override
    public void executeListener(GuildJoinEvent event) {
        this.guildsIdsManager.add(event.getGuild().getIdLong(), 0);
    }

}
