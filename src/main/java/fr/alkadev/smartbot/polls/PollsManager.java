package fr.alkadev.smartbot.polls;

import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;

public class PollsManager {

    private final HashMap<Long, Poll> polls;

    public PollsManager() {
        polls = new HashMap<>();
    }

    public boolean hasPoll(User user) {
        return this.polls.containsKey(user.getIdLong());
    }

    public void createPoll(User user) {
        this.polls.put(user.getIdLong(), new Poll(user));
    }

    public void removePoll(long userId) {
        this.polls.remove(userId);
    }

    public void setColor(User user, String color) {
        if (this.hasPoll(user)) {
            this.polls.get(user.getIdLong()).setColor(color);
        }
    }

}
