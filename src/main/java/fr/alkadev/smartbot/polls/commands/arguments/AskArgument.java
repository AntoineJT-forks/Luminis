package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

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
        return "Définir la question du sondage.";
    }

    protected void executeHasPollAction(Message message, String[] args) {
        this.pollsManager.get(message.getAuthor().getIdLong()).withQuestion(String.join(" ", args));
        MessageSender.sendPrivateMessage(message.getAuthor(), "La question du sondage a bien été changée.");
    }

}
