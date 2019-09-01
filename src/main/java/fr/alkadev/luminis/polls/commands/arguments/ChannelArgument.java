package fr.alkadev.luminis.polls.commands.arguments;

import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.system.model.GuildChannelsIds;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;

import java.util.List;

public class ChannelArgument extends PollCommandArgument {

    private final LuminisManager<GuildChannelsIds, Integer> channelsIdsManager;
    private final LuminisManager<Integer, Long> guildsIdsManager;

    @SuppressWarnings("unchecked")
    public ChannelArgument(LuminisManager channelsIdsManager, LuminisManager guildsIdsManager) {
        super(null);
        this.channelsIdsManager = (LuminisManager<GuildChannelsIds, Integer>) channelsIdsManager;
        this.guildsIdsManager = (LuminisManager<Integer, Long>) guildsIdsManager;
    }

    @Override
    public String getCommand() {
        return "channel";
    }

    @Override
    public String getDescription() {
        return "Change le channel où sont envoyés les sondage.";
    }

    @Override
    public boolean isAuthorizedChannel(MessageChannel messageChannel) {
        return messageChannel.getType() == ChannelType.TEXT;
    }

    @Override
    public boolean isAuthorizedMember(Member member) {
        return member.hasPermission(Permission.ADMINISTRATOR);
    }

    @Override
    public void execute(Message message, String[] args) {

        List<TextChannel> mentionedChannels = message.getMentionedChannels();

        if (mentionedChannels.size() != 0) {
            int guildId = this.guildsIdsManager.get(message.getGuild().getIdLong());

            this.channelsIdsManager.get(guildId).put("polls", mentionedChannels.get(0).getIdLong());
            MessageSender.sendMessage(message.getChannel(), "Le salon des sondages a bien été défini à " + mentionedChannels.get(0).getAsMention() + ".");
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "*poll channel <#mention du channel>");
    }

}
