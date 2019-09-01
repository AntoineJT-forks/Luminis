package fr.alkadev.luminis.polls;

import java.util.Map;

public class PollBuilder {

    long guildId;
    PollColor color;
    String question;
    Map<Integer, String> choices;
    Map<Integer, String> emotes;

    public PollBuilder withGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public PollBuilder withColor(String color) {
        this.color = PollColor.fromString(color);
        return this;
    }

    public PollBuilder withQuestion(String question) {
        this.question = question;
        return this;
    }

    public PollBuilder addChoice(int choiceNumber, String choice) {
        this.choices.put(choiceNumber, choice);
        return this;
    }

    public PollBuilder addEmote(int emoteNumber, String emote) {
        this.emotes.put(emoteNumber, emote);
        return this;
    }

    public PollBuilder removeChoice(int choiceNumber) {
        this.choices.remove(choiceNumber);
        this.emotes.remove(choiceNumber);
        return this;
    }

}
