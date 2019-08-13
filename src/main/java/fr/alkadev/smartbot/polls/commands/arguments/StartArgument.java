package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public class StartArgument extends PollCommandArgument {

    public StartArgument(SmartBotManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Démarre la création d'un sondage.";
    }

    @Override
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> MessageSender.sendPrivateMessage(message.getAuthor(), "Vous avez déjà un sondage en cours de création.");
    }

    @Override
    protected BiConsumer<Message, String[]> getHasNotPollAction() {
        return (message, args) -> MessageSender.sendPrivateMessage(message.getAuthor(), "Création d'un sondage.",
                sentMessage -> this.pollsManager.add(message.getAuthor().getIdLong()),
                throwable -> MessageSender.sendMessage(message.getChannel(), message.getAuthor().getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage."));
    }

}
