package fr.alkadev.smartbot.reminds;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.utils.TimeParser;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;

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

        User user = message.getAuthor();
        TextChannel channel = message.getTextChannel();

        if (args.length < 2) {
            channel.sendMessage("L'utilisation de la commande se fait comme tel : !remind <time> <message>").queue();
            return;
        }

        long timeToMillis = TimeParser.parsePeriod(args[0]);
        String reminder = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        Remind remind = new RemindBuilder(user)
                .withChannel(channel)
                .withTime(timeToMillis)
                .withMessage(reminder)
                .build();

        remind.sendRemind();

    }

}
