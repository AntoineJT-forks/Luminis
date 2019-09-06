package fr.alkadev.luminis;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.function.Predicate;

public enum CommandCategory {

    SYSTEM("System"),
    GUILD("Server", "Vous ne pouvez pas éxécuter cette commande en dehors d'un serveur", commandEvent -> commandEvent.getGuild() != null);

    public final Command.Category category;

    CommandCategory(String name, String failMessage, Predicate<CommandEvent> predicate) {
        category = new Command.Category(name, failMessage, predicate);
    }

    CommandCategory(String name) {
        category = new Command.Category(name);
    }

}
