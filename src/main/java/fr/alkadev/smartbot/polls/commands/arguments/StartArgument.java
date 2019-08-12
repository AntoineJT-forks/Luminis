package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

import java.util.function.BiConsumer;

public class StartArgument extends PollCommandArgument {

    public StartArgument(PollsManager pollsManager) {
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
    protected BiConsumer<Message, String[]> getHasPollAction() {
        return (message, args) -> message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Vous avez déjà un sondage en cours de création.").queue());
    }

    @Override
    protected BiConsumer<Message, String[]> getHasNotPollAction() {
        return (message, args) -> message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Création d'un sondage.").queue(
                sentMessage -> this.pollsManager.createPoll(message.getAuthor()),
                throwable -> message.getChannel().sendMessage(message.getAuthor().getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage.").queue())
        );
    }

}
