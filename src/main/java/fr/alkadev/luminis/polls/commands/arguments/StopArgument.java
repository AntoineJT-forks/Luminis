package fr.alkadev.luminis.polls.commands.arguments;

import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.api.entities.Message;

public class StopArgument extends PollCommandArgument {

    public StopArgument(LuminisManager pollsManager) {
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

    protected void executeHasPollAction(Message message, String[] args) {
        this.pollsManager.remove(message.getAuthor().getIdLong());
        MessageSender.sendPrivateMessage(message.getAuthor(), "Sondage terminé et envoyé.");
    }

}
