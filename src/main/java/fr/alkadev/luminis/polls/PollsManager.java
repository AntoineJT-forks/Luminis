package fr.alkadev.luminis.polls;

import fr.alkadev.luminis.system.managers.LuminisManager;

import java.util.HashMap;

public class PollsManager implements LuminisManager<PollBuilder, Long> {

    private final HashMap<Long, PollBuilder> polls;

    public PollsManager() {
        this.polls = new HashMap<>();
    }

    @Override
    public boolean isPresent(Long userId) {
        return this.polls.containsKey(userId);
    }

    @Override
    public PollBuilder get(Long userId) {
        return this.polls.get(userId);
    }

    @Override
    public void add(Long userId, PollBuilder poll) {
        this.polls.put(userId, poll);
    }

    @Override
    public void remove(Long userId) {
        this.polls.remove(userId);
    }

}
