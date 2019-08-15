package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.utils.MessageSender;
import fr.alkadev.smartbot.utils.TimeParser;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class RemindCommand implements CommandRestricted {

    @Override
    public String getCommand() {
        return "remind";
    }

    @Override
    public String getDescription() {
        return "Créer un rappel";
    }

    @Override
    public void execute(Message message, String[] args) {

        MessageChannel channel = message.getChannel();

        if (args.length >= 2) {

            long delay = TimeParser.parsePeriod(args[0]);
            String remindMessageContent = message.getAuthor().getAsMention() + " " + String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            channel.sendMessage(remindMessageContent).queueAfter(delay, TimeUnit.MILLISECONDS);

            MessageSender.sendMessage(channel, "Votre rappel a été enregistré dans notre système avec succès. ");
            return;
        }

        channel.sendMessage("L'utilisation de la commande se fait comme tel : *remind <time> <message>").queue();
    }

}