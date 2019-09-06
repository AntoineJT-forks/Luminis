package fr.alkadev.luminis.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;

@Author("Luka")
@CommandInfo(name = "cancel", description = "cancel current poll")
public class CancelArgument extends PollCommandArgument {

    public CancelArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "cancel";
        this.help = "Annuler un sondage";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = false;
    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {
        this.pollsManager.remove(event.getAuthor().getIdLong());

        event.replyInDm("Sondage annulé.");
    }

}
