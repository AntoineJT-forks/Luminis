package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public class StopArgument extends PollCommandArgument {

    public StopArgument(SmartBotManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Annuler la création d'un sondage.";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> {
            User user = message.getAuthor();
            this.pollsManager.remove(user.getIdLong());
            MessageSender.sendPrivateMessage(user, "Annulation du sondage");
        };
    }

}
