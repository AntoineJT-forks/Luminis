package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

public class EmptyArgument implements CommandRestricted {

    private final char prefix;

    EmptyArgument(char prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getCommand() {
        return " ";
    }

    @Override
    public String getDescription() {
        return "Envoie un message d'aide.";
    }

    @Override
    public void execute(Message message, String[] args) {
        message.getChannel().sendMessage("<" + this.prefix + "poll help> to get the arguments list.").queue();
    }

}
