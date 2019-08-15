package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;

public class ChoiceArgument extends PollCommandArgument {

    public ChoiceArgument(SmartBotManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "choice";
    }

    @Override
    public String getDescription() {
        return "Changer un choix.";
    }

    @Override
    protected void executeHasPollAction(Message message, String[] args) {

        if (this.isFirstArgCorrect(args)) {
            this.executeChoiceAction(message, args);
            return;
        }

        String errorMessage = "*poll choice <numéro du choix> <optionnel : description du choix>.";
        MessageSender.sendPrivateMessage(message.getAuthor(), errorMessage);
    }

    private boolean isFirstArgCorrect(String[] args) {

        try {
            Integer.parseInt(args[0]);
            return true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
            return false;
        }

    }

    private void executeChoiceAction(Message message, String[] args) {

        if (!this.getChoice(args).isEmpty()) {
            this.setChoice(message, args);
            return;
        }

        this.removeChoice(message, args);
    }

    private void setChoice(Message message, String[] args) {
        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).setChoice(choiceNumber, this.getChoice(args));
        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été enregistré.");
    }

    private void removeChoice(Message message, String[] args) {
        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).removeChoice(choiceNumber);
        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été supprimé.");
    }

    private String getChoice(String[] args) {
        return String.join(" ", Arrays.copyOfRange(args, 1, args.length));
    }

}
