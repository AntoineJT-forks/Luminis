package fr.alkadev.luminis.system.commands;

import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;

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

        MessageSender.sendMessage(message.getChannel(), "Le SmartBot a été développé par Alexandre, AntoineJT and Luka.");

    }

}
