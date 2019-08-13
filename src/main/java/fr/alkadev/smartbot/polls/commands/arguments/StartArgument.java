package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

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
    protected void executeHasNotPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), "Création d'un sondage.",
                sentMessage -> this.pollsManager.add(message.getAuthor().getIdLong()),
                throwable -> MessageSender.sendMessage(message.getChannel(), message.getAuthor().getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage."));
    }

    @Override
    protected String getValidationMessage() {
        return "Vous avez déjà un sondage en cours de création.";
    }

}
