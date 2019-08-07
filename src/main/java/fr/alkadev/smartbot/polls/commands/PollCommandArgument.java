package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final PollsManager pollsManager;
    private final BiConsumer<Message, String[]> pollsManagerAction;

    protected PollCommandArgument(PollsManager pollsManager, BiConsumer<Message, String[]> pollsManagerAction) {
        this.pollsManager = pollsManager;
        this.pollsManagerAction = pollsManagerAction;
    }

    @Override
    public void execute(Message message, String[] args) {
        this.getAction(message.getAuthor()).accept(message, args);
    }

    private BiConsumer<Message, String[]> getAction(User user) {

        BiConsumer<Message, String[]> consumer;
        consumer = (message, args) -> message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Vous n'avez pas de sondage en cours de création.").queue());

        if (!this.pollsManager.hasPoll(user)) {
            consumer = this.pollsManagerAction;
        }

        return consumer;
    }

}
