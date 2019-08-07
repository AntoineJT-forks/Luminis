package fr.alkadev.smartbot.polls;

import net.dv8tion.jda.core.entities.User;

class Poll {

    private final User user;
    private PollColor color = PollColor.WHITE;
    private String question;

    Poll(User user) {
        this.user = user;
    }

    void setColor(String color) {
        this.color = PollColor.fromString(color);
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("La couleur du sondage a bien été changée.").queue());
    }

    void setQuestion(String question) {
        this.question = question;
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("La question du sondage a bien été changée.").queue());
    }

}
