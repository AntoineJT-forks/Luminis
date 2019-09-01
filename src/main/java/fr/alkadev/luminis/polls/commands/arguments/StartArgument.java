package fr.alkadev.luminis.polls.commands.arguments;

import fr.alkadev.luminis.polls.PollBuilder;
import fr.alkadev.luminis.polls.commands.PollCommandArgument;
import fr.alkadev.luminis.system.managers.LuminisManager;
import fr.alkadev.luminis.utils.MessageSender;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public class StartArgument extends PollCommandArgument {

    public StartArgument(LuminisManager pollsManager) {
        super(pollsManager);
    }

    @Override
    public String getCommand() {
        return "start";
    }

    @Override
    public String getDescription() {
        return "Démarre la création d'un sondage.";
    }

    @Override
    public boolean isAuthorizedChannel(MessageChannel messageChannel) {
        return messageChannel.getType() == ChannelType.TEXT;
    }

    @Override
    protected void executeHasNotPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), "Création d'un sondage.",
                sentMessage -> this.pollsManager.add(message.getAuthor().getIdLong(), new PollBuilder().withGuildId(message.getGuild().getIdLong())),
                throwable -> MessageSender.sendMessage(message.getChannel(), message.getAuthor().getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage."));
    }

    @Override
    protected void executeHasPollAction(Message message, String[] args) {
        MessageSender.sendPrivateMessage(message.getAuthor(), "Vous avez déjà un sondage en cours de création.");
    }

}
