package fr.alkadev.luminis.core.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import java.util.List;

public abstract class CommandsManager {

    public void registerCommands(CommandClientBuilder clientBuilder) {

        this.getCommands().forEach(clientBuilder::addCommand);

    }

    protected abstract List<Command> getCommands();

}