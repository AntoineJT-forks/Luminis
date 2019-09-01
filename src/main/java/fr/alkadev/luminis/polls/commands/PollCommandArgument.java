package fr.alkadev.luminis.polls.commands;

import fr.alkadev.luminis.commands.CommandRestricted;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final LuminisManager<PollBuilder, Long> pollsManager;

    protected PollCommandArgument(LuminisManager<PollBuilder, Long> pollsManager) {
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
