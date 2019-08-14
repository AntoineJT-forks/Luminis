package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.ChannelType;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.ChannelsIdsManager;
import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;

import java.util.List;

public class ChannelArgument extends PollCommandArgument {

    private final ChannelsIdsManager channelsIdsManager;
    private final GuildsIdsManager guildsIdsManager;

    public ChannelArgument(SmartBotManager channelsIdsManager, SmartBotManager guildsIdsManager) {
        super(null);
        this.channelsIdsManager = (ChannelsIdsManager) channelsIdsManager;
        this.guildsIdsManager = (GuildsIdsManager) guildsIdsManager;
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
        return ChannelType.GUILD.isAuthorizedChannel(this, messageChannel);
    }

    @Override
    public boolean isAuthorizedMember(Member member) {
        return member.hasPermission(Permission.ADMINISTRATOR);
    }

    @Override
    public void execute(Message message, String[] args) {

        List<TextChannel> mentionedChannels = message.getMentionedChannels();

        if (mentionedChannels.size() == 0) {
            MessageSender.sendMessage(message.getChannel(), "*poll channel <#mention du channel>");
            return;
        }

        int guildId = this.guildsIdsManager.get(message.getGuild().getIdLong()).orElse(0);

        this.channelsIdsManager.get(guildId).ifPresent(map -> {
            map.put("polls", mentionedChannels.get(0).getIdLong());
            MessageSender.sendMessage(message.getChannel(), "Le salon des sondages a bien été défini à " + mentionedChannels.get(0).getAsMention() + ".");
        });

    }

}
