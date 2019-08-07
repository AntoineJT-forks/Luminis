package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;

public class ColorArgument extends PollCommandArgument {

    public ColorArgument(PollsManager pollsManager) {
        super(pollsManager, (message, args) -> {
            if (args.length == 0) args = new String[]{""};
            pollsManager.setColor(message.getAuthor(), args[0]);
        });
    }

    @Override
    public String getCommand() {
        return "color";
    }

    @Override
    public String getDescription() {
        return "Change la couleur du sondage.";
    }

}
