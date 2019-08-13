package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
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
        this.pollsManager.get(message.getAuthor().getIdLong()).ifPresent(poll -> poll.setQuestion(String.join(" ", args)));
        super.executeHasPollAction(message, args);
    }

    @Override
    protected String getValidationMessage() {
        return "La question du sondage a bien été changée.";
    }

}
