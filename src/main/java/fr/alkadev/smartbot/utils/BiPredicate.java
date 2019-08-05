package fr.alkadev.smartbot.utils;

import fr.alkadev.smartbot.commands.CommandExecutor;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public interface BiPredicate {

    boolean test(CommandExecutor commandExecutor, MessageChannel message);

}
