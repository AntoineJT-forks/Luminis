package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.commands.arguments.PollCommandArgumentsManager;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;

public class PollCommand implements CommandRestricted {

    private final PollCommandArgumentsManager pollCommandArgumentsManager;

    public PollCommand(char prefix) {
        pollCommandArgumentsManager = new PollCommandArgumentsManager(prefix);
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
                .ifPresent(commandRestricted -> commandRestricted.execute(message, Arrays.copyOfRange(finalArgs, 1, finalArgs.length)));

    }

}
