package fr.alkadev.luminis.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;

public interface CommandRestricted extends Command {

    default boolean isAuthorizedChannel(MessageChannel messageChannel) {
        return true;
    }

    default boolean isAuthorizedMember(Member member) {
        return true;
    }

}
