package fr.alkadev.smartbot.commands;

import net.dv8tion.jda.core.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements CommandExecutor {

    private List<Command> commands;

    HelpCommand(List<? extends Command> commandsExecutors) {
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
                .orElse("Aucune commande n'est disponible.")).queue();

    }

}
