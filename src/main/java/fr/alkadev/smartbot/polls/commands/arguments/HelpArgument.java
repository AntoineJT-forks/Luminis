package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

import java.util.List;

public class HelpArgument implements CommandRestricted {

    private final List<CommandRestricted> pollCommandArguments;

    HelpArgument(List<CommandRestricted> pollCommandArguments) {
        this.pollCommandArguments = pollCommandArguments;
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Give all poll command's arguments.";
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
