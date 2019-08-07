package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;

public class AskArgument extends PollCommandArgument {

    public AskArgument(PollsManager pollsManager) {
        super(pollsManager, (message, args) -> pollsManager.setQuestion(message.getAuthor(), String.join(" ", args)));
    }

    @Override
    public String getCommand() {
        return "ask";
    }

    @Override
    public String getDescription() {
        return "Définir la question du sondage";
    }

}
