package fr.alkadev.luminis.addons.system.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.core.commands.LuminisCommand;
import fr.alkadev.luminis.utils.TimeParser;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Author("Alexandre")
@CommandInfo(name = "remind", description = "create a remind")
public class RemindCommand extends LuminisCommand {

    public RemindCommand() {
        this.name = "remind";
        this.help = "Créer un rappel.";
        this.category = CommandCategory.SYSTEM.category;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {

        if (args.length >= 2) {

            event.reply("Votre rappel a été enregistré dans notre système avec succès. ");
            sendRemindMessage(event, args, event.getChannel());
            return;
        }

        event.replyWarning("L'utilisation de la commande se fait comme tel : *remind <time> <message>");
    }

    private void sendRemindMessage(CommandEvent event, String[] args, MessageChannel channel) {
        long delay = TimeParser.parsePeriod(args[0]);

        String remindMessage = computeRemindMessage(event.getMessage().getAuthor(), args);
        channel.sendMessage(remindMessage).queueAfter(delay, TimeUnit.MILLISECONDS);
    }

    @NotNull
    private String computeRemindMessage(User author, String[] args) {
        String remindMessage = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        return author.getAsMention() + " " + remindMessage;
    }

}
