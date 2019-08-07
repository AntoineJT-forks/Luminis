package fr.alkadev.smartbot.polls;

import net.dv8tion.jda.core.entities.User;

class Poll {

    private final User user;
    private PollColor color = PollColor.WHITE;

    Poll(User user) {
        this.user = user;
    }

    void setColor(String color) {
        this.color = PollColor.fromString(color);
    }

}
