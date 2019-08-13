package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class ChoiceArgument extends PollCommandArgument {

    private final String errorMessage = "*poll choice <numéro du choix> <optionnel : description du choix>.";

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
        this.executeAction(message, args);
    }

    private void executeAction(Message message, String[] args) {
        BiConsumer<Message, String[]> action;

        action = (sentMessage, sentArgs) -> MessageSender.sendPrivateMessage(message.getAuthor(), this.errorMessage);

        if (args.length != 0 && args[0].matches("[0-9]+")) {
            action = this::executeChoiceAction;
        }

        action.accept(message, args);
    }

    private void executeChoiceAction(Message message, String[] args) {
        BiConsumer<Message, String[]> action;

        action = this::setChoice;

        if (this.getChoice(args).isEmpty()) {
            action = this::removeChoice;
        }

        action.accept(message, args);
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
