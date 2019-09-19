package fr.alkadev.luminis.addons.polls.model;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Map;

public class PollBuilder {

    long guildId;
    PollColor color = PollColor.WHITE;
    String question;
    Map<Integer, String> choices = new HashMap<>();
    Map<Integer, String> emotes = new HashMap<>();

    public PollBuilder withGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public void withColor(String color) {
        this.color = PollColor.fromString(color);
    }

    public void withQuestion(String question) {
        this.question = question;
        if (question.isEmpty()) this.question = "";
    }

    public void addChoice(int choiceNumber, String choice) {
        this.choices.put(choiceNumber, choice);
        this.emotes.put(choiceNumber, "");
    }

    public void addEmote(int emoteNumber, String emote) {
        this.emotes.put(emoteNumber, emote);
    }

    public void removeChoice(int choiceNumber) {
        this.choices.remove(choiceNumber);
        this.emotes.remove(choiceNumber);
    }

    public Message toMessage(User user) {

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setColor(this.color.awtColor)
                .setTitle(this.question)
                .setFooter(user.getName(), user.getAvatarUrl());

        this.choices.forEach((numberChoice, choice) -> embedBuilder
                .getDescriptionBuilder()
                .append(this.emotes.get(numberChoice))
                .append(" ")
                .append(choice)
                .append("\n\n"));

        return new MessageBuilder().setEmbed(embedBuilder.build()).build();

    }

    public long getGuildId() {
        return guildId;
    }

}
