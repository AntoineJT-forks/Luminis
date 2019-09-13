package fr.alkadev.luminis.addons.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.addons.polls.PollBuilder;
import fr.alkadev.luminis.addons.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import net.dv8tion.jda.api.entities.User;

@Author("Luka")
@CommandInfo(name = "start", description = "start a poll")
public class StartArgument extends PollCommandArgument {

    public StartArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "start";
        this.help = "Démarre la création d'un sondage.";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = true;
    }

    @Override
    protected void executeHasNotPollAction(CommandEvent event, String[] args) {
        User author = event.getAuthor();
        event.replyInDm("Création d'un sondage.",
                sentMessage -> {
                    this.pollsManager.add(author.getIdLong(), new PollBuilder().withGuildId(event.getGuild().getIdLong()));
                    super.updatePoll(author);
                },
                throwable -> event.replyWarning(author.getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage."));

    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {
        event.replyInDm("Vous avez déjà un sondage en cours de création.");
    }

}
