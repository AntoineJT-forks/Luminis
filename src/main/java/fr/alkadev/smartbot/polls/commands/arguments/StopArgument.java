package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;

public class StopArgument extends PollCommandArgument {

    public StopArgument(PollsManager pollsManager) {
        super(pollsManager, (message, args) -> pollsManager.removePoll(message.getAuthor()));
    }

    @Override
    public String getCommand() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Annuler la création d'un sondage.";
    }

}
