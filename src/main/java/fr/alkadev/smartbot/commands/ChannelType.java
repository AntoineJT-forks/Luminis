package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.function.BiPredicate;

public enum ChannelType {
    
    ALL((commandExecutor, messageChannel) -> messageChannel.getType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE
            || commandExecutor.getAuthorizedChannelsNames().contains(messageChannel.getName())
            || commandExecutor.getAuthorizedChannelsNames().isEmpty()),

    GUILD((commandExecutor, messageChannel) -> messageChannel.getType() != net.dv8tion.jda.core.entities.ChannelType.PRIVATE
            && (commandExecutor.getAuthorizedChannelsNames().contains(messageChannel.getName())
            || commandExecutor.getAuthorizedChannelsNames().isEmpty())),

    PRIVATE((commandExecutor, messageChannel) -> messageChannel.getType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE);

    private final BiPredicate<CommandRestricted, MessageChannel> isAuthorizedChannel;

    ChannelType(BiPredicate<CommandRestricted, MessageChannel> isAuthorizedChannel) {
        this.isAuthorizedChannel = isAuthorizedChannel;
    }

    public boolean isAuthorizedChannel(CommandRestricted commandExecutor, MessageChannel messageChannel) {
        return this.isAuthorizedChannel.test(commandExecutor, messageChannel);
    }

}
