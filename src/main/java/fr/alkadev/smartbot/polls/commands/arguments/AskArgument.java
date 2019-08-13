package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public class AskArgument extends PollCommandArgument {

    public AskArgument(SmartBotManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "ask";
    }

    @Override
    public String getDescription() {
        return "Définir la question du sondage";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> {
            User user = message.getAuthor();
            this.pollsManager.get(user.getIdLong()).ifPresent(poll -> poll.setQuestion(String.join(" ", args)));
            MessageSender.sendPrivateMessage(user, "La question du sondage a bien été changée.");
        };
    }

}
