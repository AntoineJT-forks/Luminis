package fr.alkadev.luminis.polls.commands.arguments;

import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;


public class AskArgument extends PollCommandArgument {

    public AskArgument(LuminisManager pollsManager) {
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
        super.updatePoll(message.getAuthor());
    }

}
