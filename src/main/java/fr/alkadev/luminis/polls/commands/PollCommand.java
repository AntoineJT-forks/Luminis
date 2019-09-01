package fr.alkadev.luminis.polls.commands;

import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;

import java.util.Arrays;
import java.util.Optional;

public class PollCommand implements CommandRestricted {

    private final PollCommandArgumentsManager pollCommandArgumentsManager;

    public PollCommand(LuminisManagers luminisManagers) {
        pollCommandArgumentsManager = new PollCommandArgumentsManager(luminisManagers);
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

        if (args.length == 0) args = new String[]{" ", ""};

        Optional<PollCommandArgument> optionalPollCommandArgument = this.pollCommandArgumentsManager.getArgumentByName(args[0]);

        if (optionalPollCommandArgument.isPresent()) {
            this.executeCommandArgument(message, args, optionalPollCommandArgument.get());
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "<*poll help> pour obtenir la liste des arguments disponibles.");
    }

    private void executeCommandArgument(Message message, String[] args, PollCommandArgument pollCommandArgument) {

        if (this.canExecute(pollCommandArgument, message)) {
            pollCommandArgument.execute(message, Arrays.copyOfRange(args, 1, args.length));
            return;
        }

        MessageSender.sendMessage(message.getChannel(), "Vous ne pouvez pas éxécuter cette commande.");
    }

    private boolean canExecute(CommandRestricted commandExecutor, Message message) {
        return commandExecutor.isAuthorizedChannel(message.getChannel()) && commandExecutor.isAuthorizedMember(message.getMember());
    }

}