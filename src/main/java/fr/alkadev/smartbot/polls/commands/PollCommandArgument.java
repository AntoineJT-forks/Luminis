package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final PollsManager pollsManager;

    protected PollCommandArgument(PollsManager pollsManager) {
        this.pollsManager = pollsManager;
    }

    @Override
    public void execute(Message message, String[] args) {
        this.getAction(message.getAuthor()).accept(message, args);
    }

    private BiConsumer<Message, String[]> getAction(User user) {

        BiConsumer<Message, String[]> consumer;
        consumer = this.getHasPollAction();

        if (!this.pollsManager.hasPoll(user)) {
            consumer = this.getHasNotPollAction();
        }

        return consumer;
    }

    protected BiConsumer<Message, String[]> getHasNotPollAction() {
        return (message, args) -> message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Vous avez déjà un sondage en cours de création.").queue());
    }

    protected BiConsumer<Message, String[]> getHasPollAction() {return null;}

}
