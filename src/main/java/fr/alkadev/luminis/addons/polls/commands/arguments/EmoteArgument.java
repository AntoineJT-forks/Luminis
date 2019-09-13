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
@CommandInfo(name = "emote", description = "change a poll's emote")
public class EmoteArgument extends PollCommandArgument {

    public EmoteArgument(LuminisManager<PollBuilder, Long> pollsManager) {
        super(pollsManager);
        this.name = "emote";
        this.help = "Changer l'emote correspondant au choix.";
        this.category = CommandCategory.POLL.category;
        this.guildOnly = false;
    }

    @Override
    protected void executeHasPollAction(CommandEvent event, String[] args) {
        this.getAction(event, args);
    }

    private void getAction(CommandEvent event, String[] args) {

        boolean areArgsCorrects = args.length > 1 && args[0].matches("^[0-9]{0,9}$");
        if (areArgsCorrects) {
            this.setEmote(event, args);
            return;
        }

        event.replyInDm("*poll emote <numéro du choix> <emote>.");
    }

    private void setEmote(CommandEvent event, String[] args) {

        User user = event.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).addEmote(choiceNumber, args[1]);
        super.updatePoll(event.getAuthor());
        event.replyInDm("L'emote du choix numéro " + choiceNumber + " a bien été enregistré.");
    }

}
