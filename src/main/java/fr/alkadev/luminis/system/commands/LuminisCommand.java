package fr.alkadev.luminis.system.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Arrays;

public abstract class LuminisCommand extends Command {

    @Override
    protected void execute(CommandEvent event) {

        String contentDisplay = event.getMessage().getContentDisplay();
        String command = contentDisplay.substring(contentDisplay.indexOf(this.name));
        String[] contentArray = command.split(" ");

        String[] args = Arrays.copyOfRange(contentArray, 1, contentArray.length);

        this.execute(event, args);

    }

    protected abstract void execute(CommandEvent event, String[] args);

}
