package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.commands.arguments.PollCommandArgumentsManager;
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
        return "Create and manage a poll.";
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
