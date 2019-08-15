package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.Poll;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final SmartBotManager<Poll, Long> pollsManager;

    @SuppressWarnings("unchecked cast")
    protected PollCommandArgument(SmartBotManager pollsManager) {
        this.pollsManager = (SmartBotManager<Poll, Long>) pollsManager;
    }

    @Override
    public void execute(Message message, String[] args) {

        if (this.pollsManager.isPresent(message.getAuthor().getIdLong())) {
            this.executeHasPollAction(message, args);
            return;
        }

        this.executeHasNotPollAction(message, args);
    }

    protected void executeHasNotPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), "Vous n'avez pas de sondage en cours de création.");
    }

    protected void executeHasPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), this.getValidationMessage());
    }

    protected String getValidationMessage() {
        return "";
    }

}
