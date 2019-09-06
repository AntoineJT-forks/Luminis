package fr.alkadev.luminis.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import net.dv8tion.jda.api.entities.User;

@Author("Luka")
@CommandInfo(name = "color", description = "change poll's color")
public class ColorArgument extends PollCommandArgument {

    public ColorArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "color";
        this.help = "Change la couleur du sondage.";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = false;
    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {
        User author = event.getAuthor();

        this.pollsManager.get(author.getIdLong()).withColor(args.length != 0 ? args[0] : "");
        super.updatePoll(author);
        event.replyInDm("La couleur du sondage a bien été changée.");
    }

}
