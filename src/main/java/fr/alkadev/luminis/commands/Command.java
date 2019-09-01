package fr.alkadev.luminis.commands;

import net.dv8tion.jda.api.entities.Message;

public interface Command {

    String getCommand();

    String getDescription();

    void execute(Message message, String[] args);

}
