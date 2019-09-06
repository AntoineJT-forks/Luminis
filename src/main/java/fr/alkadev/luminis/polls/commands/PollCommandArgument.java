package fr.alkadev.luminis.polls.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import fr.alkadev.luminis.system.commands.LuminisCommand;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.system.managers.LuminisManager;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public abstract class PollCommandArgument extends LuminisCommand {

    protected final LuminisManager<PollBuilder, Long> pollsManager;

    protected PollCommandArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        this.pollsManager = pollsManager;
    }

    @Override
    public void execute(CommandEvent event, String[] args) {

        if (this.pollsManager.isPresent(event.getAuthor().getIdLong())) {
            this.executeHasPollAction(event, args);
            return;
        }

        this.executeHasNotPollAction(event, args);
    }

    protected void executeHasNotPollAction(CommandEvent event, String[] args) {
        event.replyInDm("Vous n'avez pas de sondage en cours de création.");
    }

    protected void executeHasPollAction(CommandEvent event, String[] args) {
    }

    protected void updatePoll(User user) {
        Message pollMessage = this.pollsManager.get(user.getIdLong()).toMessage(user);
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(pollMessage).queue());
    }

}
