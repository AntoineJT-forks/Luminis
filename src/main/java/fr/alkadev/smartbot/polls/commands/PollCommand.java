package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Optional;

public class PollCommand implements CommandRestricted {

    private final PollCommandArgumentsManager pollCommandArgumentsManager;

    public PollCommand(SmartBotManager pollsManager) {
        pollCommandArgumentsManager = new PollCommandArgumentsManager(pollsManager);
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

        Optional<PollCommandArgument> optionalPollCommandArgument = this.pollCommandArgumentsManager.getArgumentByName(args[0]);

        if (optionalPollCommandArgument.isPresent()) {

            optionalPollCommandArgument.get().execute(message, Arrays.copyOfRange(finalArgs, 1, finalArgs.length));

        } else {

            MessageSender.sendMessage(message.getChannel(), "<*poll help> pour obtenir la liste des arguments disponibles.");

        }

    }

}
