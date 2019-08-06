package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

public class AboutCommand implements CommandRestricted {

    @Override
    public String getCommand() {
        return "about";
    }

    @Override
    public String getDescription() {
        return "Commande \"à propos\" décrivant le bot.";
    }

    @Override
    public void execute(Message message, String[] args) {

        message.getChannel().sendMessage("Le SmartBot a été développé par Alexandre, AntoineJT and Luka.").queue();

    }

}
