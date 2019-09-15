package fr.alkadev.luminis.addons.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.addons.polls.model.PollBuilder;
import fr.alkadev.luminis.addons.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.core.managers.LuminisManager;
import net.dv8tion.jda.api.entities.User;

@Author("Luka")
@CommandInfo(name = "color", description = "change poll's color")
public class ColorArgument extends PollCommandArgument {

    public ColorArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "color";
        this.help = "Change la couleur du sondage.";
        this.category = CommandCategory.POLL.category;
        this.arguments = "[couleur (anglais)]";
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
