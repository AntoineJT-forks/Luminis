package fr.alkadev.smartbot.polls.commands;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;

public abstract class PollCommandArgument implements CommandRestricted {

    protected final PollsManager pollsManager;

    protected PollCommandArgument(PollsManager pollsManager) {
        this.pollsManager = pollsManager;
    }

}
