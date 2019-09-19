package fr.alkadev.luminis.core.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class LuminisCommand extends Command {

    @Override
    protected void execute(CommandEvent event) {

        String contentDisplay = event.getMessage().getContentDisplay();
        String command = contentDisplay.substring(contentDisplay.indexOf(this.name));
        String[] contentArray = command.split(" ");

        String[] args = Arrays.copyOfRange(contentArray, 1, contentArray.length);

        this.execute(event, args);

    }

    protected String getChildrenHelp() {
        return Arrays.stream(this.children)
                .map(command -> "\t``" + command.getName() + " " + command.getArguments() + "`` - " + command.getHelp())
                .collect(Collectors.joining("\n"));
    }


    protected abstract void execute(CommandEvent event, String[] args);

}
