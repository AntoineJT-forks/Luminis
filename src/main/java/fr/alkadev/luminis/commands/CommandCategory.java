package fr.alkadev.luminis.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.function.Predicate;

public enum CommandCategory {

    SYSTEM("Système"),
    POLL("Sondages");

    public final Command.Category category;

    CommandCategory(String name, String failMessage, Predicate<CommandEvent> predicate) {
        category = new Command.Category(name, failMessage, predicate);
    }

    CommandCategory(String name) {
        category = new Command.Category(name, "", commandEvent -> true);
    }

}
