package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final PollsManager pollsManager;

    protected PollCommandArgument(SmartBotManager pollsManager) {
        this.pollsManager = (PollsManager) pollsManager;
    }

    @Override
    public void execute(Message message, String[] args) {
        BiConsumer<Message, String[]> consumer;

        consumer = this::executeHasNotPollAction;

        if (this.pollsManager.isPresent(message.getAuthor().getIdLong())) {
            consumer = this::executeHasPollAction;
        }

        consumer.accept(message, args);
    }

    protected void executeHasNotPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), "Vous n'avez pas de sondage en cours de création.");
    }

    protected void executeHasPollAction(Message message, String[] args) {MessageSender.sendPrivateMessage(message.getAuthor(), this.getValidationMessage());}

    protected String getValidationMessage() {return "";}

}
