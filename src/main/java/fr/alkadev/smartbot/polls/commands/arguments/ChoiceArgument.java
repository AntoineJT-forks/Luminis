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

        boolean isFirstArgCorrect;

        try {
            Integer.parseInt(args[0]);
            isFirstArgCorrect = true;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ignored) {
            isFirstArgCorrect = false;
        }

        if (isFirstArgCorrect) {
            this.executeChoiceAction(message, args);
        } else {
            String errorMessage = "*poll choice <numéro du choix> <optionnel : description du choix>.";
            MessageSender.sendPrivateMessage(message.getAuthor(), errorMessage);
        }

    }

    private void executeChoiceAction(Message message, String[] args) {

        if (this.getChoice(args).isEmpty()) {
            this.removeChoice(message, args);
        } else {
            this.setChoice(message, args);
        }

    }

    private void removeChoice(Message message, String[] args) {
        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).ifPresent(poll -> poll.removeChoice(choiceNumber));

        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été supprimé.");
    }

    private void setChoice(Message message, String[] args) {
        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).ifPresent(poll -> poll.setChoice(choiceNumber, this.getChoice(args)));

        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été enregistré.");
    }

    private String getChoice(String[] args) {
        return String.join(" ", Arrays.copyOfRange(args, 1, args.length));
    }

}