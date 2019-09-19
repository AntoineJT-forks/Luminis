package fr.alkadev.luminis.addons.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.addons.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.addons.polls.model.PollsManager;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.core.managers.LuminisManager;
import fr.alkadev.luminis.core.model.GuildChannelsIds;
import net.dv8tion.jda.api.entities.TextChannel;

@Author("Luka")
@CommandInfo(name = "finish", description = "valid a poll")
public class FinishArgument extends PollCommandArgument {

    private final LuminisManager<Integer, Long> guildsIdsManager;
    private final LuminisManager<GuildChannelsIds, Integer> channelsIdsManager;

    public FinishArgument(PollsManager pollsManager, LuminisManager<Integer, Long> guildsIdsManager, LuminisManager<GuildChannelsIds, Integer> channelsIdsManager) {
        super(pollsManager);
        this.guildsIdsManager = guildsIdsManager;
        this.channelsIdsManager = channelsIdsManager;

        this.name = "finish";
        this.help = "Valider un sondage";
        this.category = CommandCategory.POLL.category;
        this.arguments = "";
        this.guildOnly = false;
    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {

        TextChannel channel = event.getJDA().getTextChannelById(getChannelId(event));

        if (channel != null) {

            channel.sendMessage(this.pollsManager.get(event.getAuthor().getIdLong()).toMessage(event.getAuthor())).queue();

        }

    }

    private Long getChannelId(CommandEvent event) {
        long guildIdLong = this.pollsManager.get(event.getAuthor().getIdLong()).getGuildId();
        int guildId = this.guildsIdsManager.get(guildIdLong);
        return this.channelsIdsManager.get(guildId).getChannelsIds().get("polls");
    }

}
