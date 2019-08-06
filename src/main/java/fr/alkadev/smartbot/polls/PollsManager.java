package fr.alkadev.smartbot.polls;

import net.dv8tion.jda.core.entities.User;

import java.util.HashMap;

public class PollsManager {

    private final HashMap<Long, Poll> polls;

    public PollsManager() {
        polls = new HashMap<>();
    }

    public void createPoll(User user) {
        this.polls.put(user.getIdLong(), new Poll(user));
    }

    public boolean hasPoll(User user) {
        return this.polls.containsKey(user.getIdLong());
    }

}
