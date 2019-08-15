package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.system.model.GuildChannelsIds;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;

import java.util.List;

public class ChannelArgument extends PollCommandArgument {

    private final SmartBotManager<GuildChannelsIds, Integer> channelsIdsManager;
    private final SmartBotManager<Integer, Long> guildsIdsManager;

    @SuppressWarnings("unchecked")
    public ChannelArgument(SmartBotManager channelsIdsManager, SmartBotManager guildsIdsManager) {
        super(null);
        this.channelsIdsManager = (SmartBotManager<GuildChannelsIds, Integer>) channelsIdsManager;
        this.guildsIdsManager = (SmartBotManager<Integer, Long>) guildsIdsManager;
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
