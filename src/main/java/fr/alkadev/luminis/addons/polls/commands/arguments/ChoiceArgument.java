package fr.alkadev.luminis.addons.polls.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.addons.polls.model.PollBuilder;
import fr.alkadev.luminis.addons.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.core.managers.LuminisManager;
import net.dv8tion.jda.api.entities.User;

import java.util.Arrays;

@Author("Luka")
@CommandInfo(name = "choice", description = "change the value of a choice")
public class ChoiceArgument extends PollCommandArgument {

    public ChoiceArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "choice";
        this.help = "Changer un choix.";
        this.category = CommandCategory.POLL.category;
        this.arguments = "[numéro du choix] [rien - choix]";
        this.guildOnly = false;
    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {

        boolean isFirstArgCorrect = args.length != 0 && args[0].matches("^[0-9]{0,9}$");
        if (isFirstArgCorrect) {
            this.executeChoiceAction(event, args);
            return;
        }

        event.replyInDm("*poll choice <numéro du choix> <optionnel : description du choix>.");
    }

    private void executeChoiceAction(CommandEvent event, String[] args) {

        int choiceNumber = Integer.parseInt(args[0]);
        String choice = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (!choice.isEmpty()) {
            event.replyInDm(this.setChoice(event.getAuthor(), choiceNumber, choice));
            return;
        }

        event.replyInDm(this.removeChoice(event.getAuthor(), choiceNumber));
    }

    private String setChoice(User author, int choiceNumber, String choice) {

        PollBuilder pollBuilder = this.pollsManager.get(author.getIdLong());
        pollBuilder.addChoice(choiceNumber, choice);

        updatePoll(author);
        return "Le choix numéro " + choiceNumber + " a bien été enregistré.";
    }

    private String removeChoice(User author, int choiceNumber) {

        PollBuilder pollBuilder = this.pollsManager.get(author.getIdLong());
        pollBuilder.removeChoice(choiceNumber);

        updatePoll(author);
        return "Le choix numéro " + choiceNumber + " a bien été supprimé.";
    }

}
