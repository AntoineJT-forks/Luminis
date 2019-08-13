package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import fr.alkadev.smartbot.system.managers.SmartBotManager;
import fr.alkadev.smartbot.utils.MessageSender;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.function.BiConsumer;

public class EmoteArgument extends PollCommandArgument {

    private final String errorMessage = "*poll emote <numéro du choix> <emote>.";

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
        BiConsumer<Message, String[]> action;

        action = (sentMessage, sentArgs) -> MessageSender.sendPrivateMessage(message.getAuthor(), errorMessage);

        if (args.length != 2 && args[0].matches("[0-9]+")) {
            action = this::setEmote;
        }

        action.accept(message, args);
    }

    private void setEmote(Message message, String[] args) {

        User user = message.getAuthor();
        int choiceNumber = Integer.parseInt(args[0]);

        this.pollsManager.get(user.getIdLong()).ifPresent(poll -> poll.setEmote(choiceNumber, args[1]));

        MessageSender.sendPrivateMessage(user, "Le choix numéro " + choiceNumber + " a bien été enregistré.");
    }

}
