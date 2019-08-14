package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.events.Listener;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;

public class GuildLeaveListener implements Listener<GuildLeaveEvent> {

    private final GuildsIdsManager guildsIdsManager;

    public GuildLeaveListener(SmartBotManager guildsIdsManager) {
        this.guildsIdsManager = (GuildsIdsManager) guildsIdsManager;
    }


    @Override
    public Class<GuildLeaveEvent> getEventClass() {
        return GuildLeaveEvent.class;
    }

    @Override
    public void executeListener(GuildLeaveEvent event) {
        this.guildsIdsManager.remove(event.getGuild().getIdLong());
    }

}
