package fr.alkadev.smartbot.polls;

public class Poll {

    private PollColor color = PollColor.WHITE;
    private String question;

    public void setColor(String color) {
        this.color = PollColor.fromString(color);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
