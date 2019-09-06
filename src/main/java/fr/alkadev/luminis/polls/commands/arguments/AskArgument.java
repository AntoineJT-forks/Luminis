package fr.alkadev.luminis.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;

@Author("Luka")
@CommandInfo(name = "add", description = "change the poll's question")
public class AskArgument extends PollCommandArgument {

    public AskArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "ask";
        this.help = "Changer la question du sondage";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = false;
    }

    protected void executeHasPollAction(CommandEvent event, String[] args) {
        PollBuilder pollBuilder = this.pollsManager.get(event.getAuthor().getIdLong());
        pollBuilder.withQuestion(String.join(" ", args));
        super.updatePoll(event.getAuthor());

        event.replyInDm("La question du sondage a bien été changée.");
    }

}
