package fr.alkadev.luminis.polls.commands.arguments;

import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;

public class EmptyArgument extends PollCommandArgument {

    public EmptyArgument() {
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
        MessageSender.sendMessage(message.getChannel(), "<*poll help> pour obtenir la liste des arguments disponibles.");
    }

}
