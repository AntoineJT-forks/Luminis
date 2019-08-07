package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;

public class PollCommand implements CommandRestricted {

    private final PollCommandArgumentsManager pollCommandArgumentsManager;

    public PollCommand() {
        pollCommandArgumentsManager = new PollCommandArgumentsManager();
    }

    @Override
    public String getCommand() {
        return "poll";
    }

    @Override
    public String getDescription() {
        return "Créer et gérer un sondage.";
    }

    @Override
    public void execute(Message message, String[] args) {

        if (args.length == 0) {
            args = new String[]{" ", ""};
        }

        String[] finalArgs = args;
        this.pollCommandArgumentsManager.getArgumentByName(args[0])
                .ifPresentOrElse(
                        commandRestricted -> commandRestricted.execute(message, Arrays.copyOfRange(finalArgs, 1, finalArgs.length)),
                        () -> message.getChannel().sendMessage("<*poll help> pour obtenir la liste des arguments disponibles.").queue());

    }

}
