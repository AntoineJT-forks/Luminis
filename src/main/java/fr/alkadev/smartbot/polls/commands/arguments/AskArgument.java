package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public class AskArgument extends PollCommandArgument {

    public AskArgument(PollsManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "ask";
    }

    @Override
    public String getDescription() {
        return "Définir la question du sondage";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> this.pollsManager.setQuestion(message.getAuthor(), String.join(" ", args));
    }

}
