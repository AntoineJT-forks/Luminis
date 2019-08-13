package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public class ColorArgument extends PollCommandArgument {

    public ColorArgument(SmartBotManager pollsManager) {
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
            User user = message.getAuthor();

            if (args.length == 0) args = new String[]{""};
            String[] finalArgs = args;
            this.pollsManager.get(user.getIdLong()).ifPresent(poll -> poll.setColor(finalArgs[0]));

            MessageSender.sendPrivateMessage(user, "La couleur du sondage a bien été changée.");

        };

    }

}
