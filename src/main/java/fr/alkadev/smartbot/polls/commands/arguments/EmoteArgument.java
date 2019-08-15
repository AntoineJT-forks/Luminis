package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class EmoteArgument extends PollCommandArgument {

    public EmoteArgument(SmartBotManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "emote";
    }

    @Override
    public String getDescription() {
        return "Changer l'emote correspondant au choix.";
    }

    @Override
    protected void executeHasPollAction(Message message, String[] args) {
        this.getAction(message, args);
    }

    private void getAction(Message message, String[] args) {

        if (this.areArgsCorrects(args)) {
            this.setEmote(message, args);
            return;
        }

        String errorMessage = "*poll emote <numéro du choix> <emote>.";
        MessageSender.sendPrivateMessage(message.getAuthor(), errorMessage);
    }

    private boolean areArgsCorrects(String[] args) {

        try {
            Integer.parseInt(args[0]);
            String.valueOf(args[1]);
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
            return false;
        }

    }

    private void setEmote(Message message, String[] args) {

        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).setEmote(choiceNumber, args[1]);
        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été enregistré.");
    }

}
