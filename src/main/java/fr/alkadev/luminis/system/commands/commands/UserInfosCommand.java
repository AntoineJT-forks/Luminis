package fr.alkadev.luminis.system.commands.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.system.commands.LuminisCommand;
import fr.alkadev.luminis.utils.LuminisOnlineStatus;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Author("Alexandre")
@CommandInfo(name = "userinfos", description = "show a user's infos")
public class UserInfosCommand extends LuminisCommand {

    public UserInfosCommand() {
        this.name = "userinfos";
        this.help = "Affiche les informations relatives à un utilisateur";
        this.arguments = "[nothing - @mention]";
        this.category = CommandCategory.SYSTEM.category;
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {

        Member member = event.getMember();
        Message message = event.getMessage();

        if (message.getMentionedMembers().size() != 0) {
            member = message.getMentionedMembers().get(0);
        }

        EmbedBuilder embedBuilder = new EmbedBuilder();

        addBasicInfos(member, embedBuilder);
        addMemberInfos(member, embedBuilder);

        event.reply(embedBuilder.build());

    }

    private void addMemberInfos(Member member, EmbedBuilder embedBuilder) {

        StringBuilder roleBuilder = new StringBuilder();
        member.getRoles().forEach(role -> roleBuilder.append(role.getName()).append(", "));

        String nickname = member.getNickname() == null ? "Aucun surnom" : member.getNickname();
        String gameName = member.getActivities().size() == 0 ? "Aucun jeu" : member.getActivities().get(0).getName();
        String creationTime = member.getTimeCreated().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        String joinTime = member.getTimeJoined().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        String roleList = member.getRoles().isEmpty() ? "Aucun rôle" : roleBuilder.toString().substring(0, roleBuilder.toString().length() - 2);

        embedBuilder.addField("ID", member.getUser().getId(), true);
        embedBuilder.addField("Pseudonyme", nickname, true);
        embedBuilder.addField("État", LuminisOnlineStatus.from(member.getOnlineStatus()).toString(), true);
        embedBuilder.addField("Joue à", gameName, true);
        embedBuilder.addField("Mention", member.getAsMention(), true);
        embedBuilder.addField("Date de création", creationTime, true);
        embedBuilder.addField("A rejoint", joinTime, true);
        embedBuilder.addField("Rôles", roleList, true);
    }

    private void addBasicInfos(Member member, EmbedBuilder embedBuilder) {
        embedBuilder.setAuthor(member.getUser().getAsTag(), null, member.getUser().getAvatarUrl());
        embedBuilder.setThumbnail(member.getUser().getAvatarUrl());
        embedBuilder.setFooter("Requête effectuée par " + member.getUser().getName(), member.getUser().getAvatarUrl());
        embedBuilder.setTimestamp(Instant.now());
        embedBuilder.setColor(Color.ORANGE);
    }

}
