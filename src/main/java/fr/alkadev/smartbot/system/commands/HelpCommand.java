package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.Command;
import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements CommandRestricted {

    private List<Command> commands;

    public HelpCommand(List<? extends Command> commandsExecutors) {
        this.commands = new ArrayList<>(commandsExecutors);
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Give all the SmartBot commands.";
    }

    @Override
    public void execute(Message message, String[] args) {

        message.getChannel().sendMessage(this.commands
                .stream()
                .map(command -> command.getCommand() + " : " + command.getDescription())
                .reduce((sentMessage, commandDescription) -> sentMessage += commandDescription + "\n")
                .map(sentMessage -> sentMessage + "\n" + this.getCommand() + " : " + this.getDescription())
                .orElse("Aucune commande n'est disponible.")).queue();

    }

}
