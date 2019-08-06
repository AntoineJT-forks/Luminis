package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

public class EmptyArgument implements CommandRestricted {

    @Override
    public String getCommand() {
        return " ";
    }

    @Override
    public String getDescription() {
        return "Help you if you don't know poll command's arguments.";
    }

    @Override
    public void execute(Message message, String[] args) {
        message.getChannel().sendMessage("<poll help> to get the arguments list.").queue();
    }

}
