package fr.alkadev.luminis.system.commands;

import fr.alkadev.luminis.commands.CommandRestricted;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class UserInfosCommand implements CommandRestricted {

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
            String gameName = member.getActivities().size() == 0  ? "Aucun jeu" : member.getActivities().get(0).getName();
            String creationTime = member.getTimeCreated().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
            String joinTime = member.getTimeJoined().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
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
