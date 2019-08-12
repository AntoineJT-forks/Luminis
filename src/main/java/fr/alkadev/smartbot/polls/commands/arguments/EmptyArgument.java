package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

public class EmptyArgument extends PollCommandArgument {

    protected EmptyArgument() {
        super(null);
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
        message.getChannel().sendMessage("<*poll help> pour obtenir la liste des arguments disponibles.").queue();
    }

}
