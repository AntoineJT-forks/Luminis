package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public class StopArgument extends PollCommandArgument {

    public StopArgument(PollsManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Annuler la création d'un sondage.";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> pollsManager.removePoll(message.getAuthor());
    }

}
