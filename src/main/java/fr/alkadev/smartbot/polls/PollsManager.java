package fr.alkadev.smartbot.polls;

import fr.alkadev.smartbot.system.managers.SmartBotManager;

import java.util.HashMap;
import java.util.Optional;

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
    public Optional<Poll> get(Long userId) {
        return Optional.ofNullable(this.polls.get(userId));
    }

    @Override
    public void add(Long userId) {
        this.polls.put(userId, new Poll());
    }

    @Override
    public void remove(Long userId) {
        this.polls.remove(userId);
    }

}
