package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;

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

    protected void executeHasPollAction(Message message, String[] args) {
        if (args.length == 0) args = new String[]{""};

        String[] finalArgs = args;
        this.pollsManager.get(message.getAuthor().getIdLong()).withColor(finalArgs[0]);
        MessageSender.sendPrivateMessage(message.getAuthor(), "La couleur du sondage a bien été changée.");
    }

}
