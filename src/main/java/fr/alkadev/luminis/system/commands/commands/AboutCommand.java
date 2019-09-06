package fr.alkadev.luminis.system.commands.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.system.commands.LuminisCommand;
import net.dv8tion.jda.api.Permission;

@Author("Luka")
@CommandInfo(name = "about", description = "command which describe the bot")
public class AboutCommand extends LuminisCommand {

    public AboutCommand() {
        this.name = "about";
        this.help = "Commande \"à propos\" décrivant le bot.";
        this.botPermissions = new Permission[]{Permission.MESSAGE_WRITE};
        this.category = CommandCategory.SYSTEM.category;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {

        event.reply("Le SmartBot a été développé par Alexandre, AntoineJT and Luka.\n" +
                "Les librairies utilisées sont :\n" +
                " - JDA (https://github.com/DV8FromTheWorld/JDA)\n" +
                " - JDA-Utilities (https://github.com/JDA-Applications/JDA-Utilities)\n" +
                " - HikariCP (https://github.com/brettwooldridge/HikariCP)\n" +
                " - JOOQ (https://github.com/jOOQ/jOOQ)\n");

    }

}
