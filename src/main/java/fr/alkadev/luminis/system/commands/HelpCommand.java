package fr.alkadev.luminis.system.commands;

import fr.alkadev.luminis.commands.Command;
import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;

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
        return "Envoie la liste des commandes disponibles.";
    }

    @Override
    public void execute(Message message, String[] args) {

        MessageSender.sendMessage(message.getChannel(), this.commands
                .stream()
                .map(command -> command.getCommand() + " : " + command.getDescription() + "\n")
                .reduce((sentMessage, commandDescription) -> sentMessage += commandDescription)
                .map(sentMessage -> sentMessage + this.getCommand() + " : " + this.getDescription())
                .orElse("Aucune commande n'est disponible."));

    }

}
