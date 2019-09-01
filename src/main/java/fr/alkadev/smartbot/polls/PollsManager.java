package fr.alkadev.smartbot.polls;

import fr.alkadev.smartbot.system.managers.SmartBotManager;

import java.util.HashMap;

public class PollsManager implements SmartBotManager<PollBuilder, Long> {

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
