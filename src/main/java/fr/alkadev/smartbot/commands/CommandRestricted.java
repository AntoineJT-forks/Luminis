package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;

public interface CommandRestricted extends Command {

    default boolean isAuthorizedChannel(MessageChannel messageChannel) {
        return true;
    }

    default boolean isAuthorizedMember(Member member) {
        return true;
    }

}
