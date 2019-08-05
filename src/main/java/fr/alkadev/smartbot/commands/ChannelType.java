package fr.alkadev.smartbot.commands;

import fr.alkadev.smartbot.utils.BiPredicate;
import net.dv8tion.jda.core.entities.MessageChannel;

public enum ChannelType {
    
    ALL((commandExecutor, messageChannel) -> messageChannel.getType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE
            || commandExecutor.getAuthorizedChannelsNames().contains(messageChannel.getName())
            || commandExecutor.getAuthorizedChannelsNames().isEmpty()),

    GUILD((commandExecutor, messageChannel) -> commandExecutor.getAuthorizedChannelsNames().contains(messageChannel.getName())
            || commandExecutor.getAuthorizedChannelsNames().isEmpty()),

    PRIVATE((commandExecutor, messageChannel) -> messageChannel.getType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE);

    private final BiPredicate isAuthorizedChannel;

    ChannelType(BiPredicate isAuthorizedChannel) {
        this.isAuthorizedChannel = isAuthorizedChannel;
    }

    public boolean isAuthorizedChannel(CommandExecutor commandExecutor, MessageChannel messageChannel) {
        return this.isAuthorizedChannel.test(commandExecutor, messageChannel);
    }

}
