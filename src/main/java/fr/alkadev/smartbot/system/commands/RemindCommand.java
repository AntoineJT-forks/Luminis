package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.entities.Message;

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
        message.getChannel().sendMessage("T'as pigé comment ajouter des cmds Alexandre").queue();

    }

}
