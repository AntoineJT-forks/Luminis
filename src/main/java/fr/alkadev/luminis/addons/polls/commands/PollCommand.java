package fr.alkadev.luminis.addons.polls.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.core.commands.LuminisCommand;

import java.util.List;
import java.util.stream.Collectors;

@Author("Luka")
@CommandInfo(name = "poll")
public class PollCommand extends LuminisCommand {

    public PollCommand(List<Command> arguments) {
        this.name = "poll";
        this.children = arguments.toArray(new Command[]{});
        this.help = "Créer et gérer un sondage.\n" + super.getChildrenHelp();
        this.category = CommandCategory.POLL.category;
        this.arguments = "[" + arguments.stream().map(Command::getName).collect(Collectors.joining(" - ")) + "]";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {
        event.reply("<*poll help> pour obtenir la liste des arguments disponibles.");
    }

}
