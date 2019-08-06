package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Message;

public interface Command {

    String getCommand();

    String getDescription();

    void execute(Message message, String[] args);

}
