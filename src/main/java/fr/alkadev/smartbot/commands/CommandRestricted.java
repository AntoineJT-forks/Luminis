package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.ArrayList;
import java.util.List;

public interface CommandRestricted extends Command {

    default boolean isAuthorizedChannel(MessageChannel messageChannel) {
        return ChannelType.ALL.isAuthorizedChannel(this, messageChannel);
    }

    default List<String> getAuthorizedChannelsNames() {
        return new ArrayList<>();
    }

    default boolean isAuthorizedMember(Member member) {
        return true;
    }

}
