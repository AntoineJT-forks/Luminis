package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public class ColorArgument extends PollCommandArgument {

    public ColorArgument(PollsManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "color";
    }

    @Override
    public String getDescription() {
        return "Change la couleur du sondage.";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {

        return (message, args) -> {
            if (args.length == 0) args = new String[]{""};
            this.pollsManager.setColor(message.getAuthor(), args[0]);
        };

    }

}
