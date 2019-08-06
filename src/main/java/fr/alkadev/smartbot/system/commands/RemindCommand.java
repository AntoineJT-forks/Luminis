package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.system.reminds.Remind;
import fr.alkadev.smartbot.system.reminds.RemindBuilder;
import fr.alkadev.smartbot.utils.TimeParser;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class RemindCommand implements CommandRestricted {

    @Override
    public String getCommand(){
        return "remind";
    }

    @Override
    public String getDescription(){
        return "Remind command which SmartBot will remind you some task";
    }

    @Override
    public void execute(Message message, String[] args){

        User user = message.getAuthor();
        TextChannel channel = message.getTextChannel();

        if(args.length < 2){
            channel.sendMessage("L'utilisation de la commande se fait comme tel !remind <time> <message>").queue();
            return;

        }

        long timeToMillis = TimeParser.parsePeriod(args[0]);
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 1; i < args.length; i++) stringBuilder.append(args[i]).append(" ");

        Remind remind = new RemindBuilder(user)
                .defineChannel(channel)
                .defineTime(timeToMillis)
                .editMessage(stringBuilder.toString())
                .build();

        remind.sendRemind();

    }

}
