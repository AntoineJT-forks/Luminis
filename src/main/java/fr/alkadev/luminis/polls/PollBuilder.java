package fr.alkadev.luminis.polls;

import java.util.HashMap;
import java.util.Map;

public class PollBuilder {

    long guildId;
    PollColor color;
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
    }

    public void addChoice(int choiceNumber, String choice) {
        this.choices.put(choiceNumber, choice);
    }

    public void addEmote(int emoteNumber, String emote) {
        this.emotes.put(emoteNumber, emote);
    }

    public void removeChoice(int choiceNumber) {
        this.choices.remove(choiceNumber);
        this.emotes.remove(choiceNumber);
    }

}
