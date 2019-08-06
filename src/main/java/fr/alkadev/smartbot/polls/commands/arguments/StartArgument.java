package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

public class StartArgument implements CommandRestricted {

    @Override
    public String getCommand() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Démarre la création d'un sondage.";
    }

    @Override
    public void execute(Message message, String[] args) {

    }

}
