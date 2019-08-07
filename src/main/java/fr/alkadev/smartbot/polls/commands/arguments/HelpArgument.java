package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

import java.util.List;

public class HelpArgument extends PollCommandArgument {

    private final List<PollCommandArgument> pollCommandArguments;

    public HelpArgument(List<PollCommandArgument> pollCommandArguments) {
        super(null, null);
        this.pollCommandArguments = pollCommandArguments;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Envoie la liste des arguments disponibles.";
    }

    @Override
    public void execute(Message message, String[] args) {

        message.getChannel().sendMessage(this.pollCommandArguments
                .stream()
                .map(command -> command.getCommand() + " : " + command.getDescription() + "\n")
                .reduce((sentMessage, commandDescription) -> sentMessage += commandDescription)
                .orElse("Aucune commande n'est disponible.")).queue();

    }

}
