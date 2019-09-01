package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

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

    protected void executeHasPollAction(Message message, String[] args) {
        this.pollsManager.remove(message.getAuthor().getIdLong());
        MessageSender.sendPrivateMessage(message.getAuthor(), "Vous avez déjà un sondage en cours de création.");
    }

}
