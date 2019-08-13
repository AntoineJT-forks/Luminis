package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final PollsManager pollsManager;

    protected PollCommandArgument(SmartBotManager pollsManager) {
        this.pollsManager = (PollsManager) pollsManager;
    }

    @Override
    public void execute(Message message, String[] args) {
        this.getAction(message.getAuthor()).accept(message, args);
    }

    private BiConsumer<Message, String[]> getAction(User user) {

        BiConsumer<Message, String[]> consumer;
        consumer = this.getHasPollAction();

        if (!this.pollsManager.isPresent(user.getIdLong())) {
            consumer = this.getHasNotPollAction();
        }

        return consumer;
    }

    protected BiConsumer<Message, String[]> getHasNotPollAction() {
        return (message, args) -> MessageSender.sendPrivateMessage(message.getAuthor(), "Vous n'avez pas de sondage en cours de création.");
    }

    protected BiConsumer<Message, String[]> getHasPollAction() {
        return null;
    }

}
