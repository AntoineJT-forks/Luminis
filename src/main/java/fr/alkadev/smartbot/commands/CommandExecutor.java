package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;

import java.util.ArrayList;
import java.util.List;

public interface CommandExecutor extends Command {

    void execute(Message message, String[] args);

    default ChannelType getChannelType() {
        return ChannelType.ALL;
    }

    default List<String> getAuthorizedChannelsNames() {
        return new ArrayList<>();
    }

    default boolean isAuthorizedMember(Member member) {
        return true;
    }

}
