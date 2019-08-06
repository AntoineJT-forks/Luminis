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
        return "About command which describe the SmartBot";
    }

    @Override
    public void execute(Message message, String[] args) {

        message.getChannel().sendMessage("The SmartBot has been developed by Alexandre, AntoineJT and Luka.").queue();

    }

}
