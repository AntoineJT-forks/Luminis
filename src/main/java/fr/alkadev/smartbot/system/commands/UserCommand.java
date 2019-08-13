package fr.alkadev.smartbot.system.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;

import java.awt.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class UserCommand implements CommandRestricted {

    @Override
    public String getCommand(){
        return "userinfo";
    }

    @Override
    public String getDescription(){
        return "Affiche les informations relatif à l'utilisateur";
    }

    @Override
    public void execute(Message message, String[] args){

        MessageChannel channel = message.getChannel();
        Member member = message.getMember();

        if(args.length > 0){

            String userID = args[0].replace("<@", "").replace(">", "");
            User user = message.getJDA().getUserById(userID);

            member = message.getGuild().getMember(user);

        }

        if(channel instanceof TextChannel){

            EmbedBuilder embedBuilder = new EmbedBuilder();

            StringBuilder roleBuilder = new StringBuilder();
            member.getRoles().forEach(role -> roleBuilder.append(role.getName()).append(", "));

            String nickname = member.getNickname() == null ? "Aucun surnom" : member.getNickname();
            String gameName = member.getGame() == null ? "Aucun jeu" : member.getGame().getName();
            String creationTime = member.getUser().getCreationTime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String joinTime = member.getJoinDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String roleList = member.getRoles().isEmpty() ? "Aucun rôle" : roleBuilder.toString().substring(0, roleBuilder.toString().length() - 2);

            embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getAvatarUrl());
            embedBuilder.setThumbnail(member.getUser().getAvatarUrl());
            embedBuilder.setFooter("Requête effectuée par " + message.getAuthor().getName(), message.getAuthor().getAvatarUrl());
            embedBuilder.setTimestamp(Instant.now());
            embedBuilder.setColor(Color.ORANGE);

            embedBuilder.addField("ID", member.getUser().getId(), true);
            embedBuilder.addField("Pseudonyme", nickname, true);
            embedBuilder.addField("État", member.getOnlineStatus().getKey(), true);
            embedBuilder.addField("Joue à", gameName, true);
            embedBuilder.addField("Mention", member.getAsMention(), true);
            embedBuilder.addField("Date de création", creationTime, true);
            embedBuilder.addField("A rejoint", joinTime, true);
            embedBuilder.addField("Rôles", roleList, true);

            channel.sendMessage(embedBuilder.build()).queue();

        }

    }

}
