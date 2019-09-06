package fr.alkadev.luminis.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.commands.CommandCategory;
import fr.alkadev.luminis.polls.commands.PollCommandArgument;

import java.util.List;

@Author("Luka")
@CommandInfo(name = "help", description = "send poll command argument list")
public class HelpArgument extends PollCommandArgument {

    private final List<PollCommandArgument> pollCommandArguments;

    public HelpArgument(List<PollCommandArgument> pollCommandArguments) {
        super(null);
        this.pollCommandArguments = pollCommandArguments;
        this.name = "help";
        this.help = "Envoie la liste des arguments disponibles.\"";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = false;
    }

    @Override
    public void execute(CommandEvent event, String[] args) {

        event.replyInDm(this.pollCommandArguments
                .stream()
                .map(command -> command.getName() + " : " + command.getHelp() + "\n")
                .reduce((sentMessage, commandDescription) -> sentMessage += commandDescription)
                .orElse("Aucune commande n'est disponible."));

    }

}
