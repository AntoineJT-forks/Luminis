package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollBuilder;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final SmartBotManager<PollBuilder, Long> pollsManager;

    protected PollCommandArgument(SmartBotManager<PollBuilder, Long> pollsManager) {
        this.pollsManager = pollsManager;
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

    protected void executeHasPollAction(Message message, String[] args) {}

}
