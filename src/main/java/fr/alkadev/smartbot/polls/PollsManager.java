package fr.alkadev.smartbot.polls;

import fr.alkadev.smartbot.system.managers.SmartBotManager;

import java.util.HashMap;

public class PollsManager implements SmartBotManager<Poll, Long> {

    private final HashMap<Long, Poll> polls;

    public PollsManager() {
        polls = new HashMap<>();
    }

    @Override
    public boolean isPresent(Long userId) {
        return this.polls.containsKey(userId);
    }

    @Override
    public Poll get(Long userId) {
        return this.polls.get(userId);
    }

    @Override
    public void add(Long userId, Poll poll) {
        this.polls.put(userId, poll);
    }

    @Override
    public void remove(Long userId) {
        this.polls.remove(userId);
    }

}
