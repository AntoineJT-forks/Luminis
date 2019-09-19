package fr.alkadev.luminis.addons.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.addons.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.core.managers.LuminisManager;
import fr.alkadev.luminis.core.model.GuildChannelsIds;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

@Author("Luka")
@CommandInfo(name = "channel", description = "change the channel where polls are sent")
public class ChannelArgument extends PollCommandArgument {

    private final LuminisManager<GuildChannelsIds, Integer> channelsIdsManager;
    private final LuminisManager<Integer, Long> guildsIdsManager;

    @SuppressWarnings("unchecked")
    public ChannelArgument(LuminisManager channelsIdsManager, LuminisManager guildsIdsManager) {
        super(null);
        this.channelsIdsManager = (LuminisManager<GuildChannelsIds, Integer>) channelsIdsManager;
        this.guildsIdsManager = (LuminisManager<Integer, Long>) guildsIdsManager;

        this.name = "channel";
        this.help = "Change le channel où sont envoyés les sondage.";
        this.userPermissions = new Permission[]{Permission.ADMINISTRATOR};
        this.category = CommandCategory.POLL.category;
        this.arguments = "[@mention du nouveau channel]";
        this.guildOnly = true;
    }

    @Override
    public void execute(CommandEvent event, String[] args) {

        List<TextChannel> mentionedChannels = event.getMessage().getMentionedChannels();

        if (mentionedChannels.size() != 0) { this.changeChannel(event, mentionedChannels.get(0));
            return;
        }

        event.reply("*poll channel <#mention du channel>");
    }

    private void changeChannel(CommandEvent event, TextChannel newPollChannel) {
        int guildId = this.guildsIdsManager.get(event.getGuild().getIdLong());

        this.channelsIdsManager.get(guildId).put("polls", newPollChannel.getIdLong());
        event.reply("Le salon des sondages a bien été défini à " + newPollChannel.getAsMention() + ".");
    }

}
