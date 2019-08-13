package fr.alkadev.smartbot.polls;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Poll {

    private PollColor color;
    private String question;
    private Map<Integer, String> choices = new TreeMap<>();
    private Map<Integer, String> emotes = new HashMap<>();

    public void setColor(String color) {
        this.color = PollColor.fromString(color);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoice(int choiceNumber, String choice) {
        this.choices.put(choiceNumber, choice);
    }

    public void removeChoice(int choiceNumber) {
        this.choices.remove(choiceNumber);
        this.emotes.remove(choiceNumber);
    }

    public void setEmote(int choiceNumber, String emote) {
        this.emotes.put(choiceNumber, emote);
    }

}
