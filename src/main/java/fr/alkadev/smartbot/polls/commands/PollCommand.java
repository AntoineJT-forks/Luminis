package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Optional;

public class PollCommand implements CommandRestricted {

    private final PollCommandArgumentsManager pollCommandArgumentsManager;

    public PollCommand(SmartBotManager pollsManager, SmartBotManager channelsIdsManager, SmartBotManager guildsIdsManager) {
        pollCommandArgumentsManager = new PollCommandArgumentsManager(pollsManager, channelsIdsManager, guildsIdsManager);
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

            PollCommandArgument pollCommandArgument = optionalPollCommandArgument.get();

            if (this.canExecute(pollCommandArgument, message)) {
                pollCommandArgument.execute(message, Arrays.copyOfRange(args, 1, args.length));
            }

        } else {

            MessageSender.sendMessage(message.getChannel(), "<*poll help> pour obtenir la liste des arguments disponibles.");

        }

    }

    private boolean canExecute(CommandRestricted commandExecutor, Message message) {
        return commandExecutor.isAuthorizedChannel(message.getChannel())
                && (message.getChannelType() == net.dv8tion.jda.core.entities.ChannelType.PRIVATE || commandExecutor.isAuthorizedMember(message.getMember()));
    }


}
