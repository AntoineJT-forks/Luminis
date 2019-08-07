package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;

public class StartArgument extends PollCommandArgument {

    public StartArgument(PollsManager pollsManager) {
        super(pollsManager, null);
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
    public void execute(Message message, String[] args) {

        if (this.pollsManager.hasPoll(message.getAuthor())) {
            message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Vous avez déjà un sondage en cours de création").queue());
            return;
        }

        message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage("Création d'un sondage.").queue(
                sentMessage -> this.pollsManager.createPoll(message.getAuthor()),
                throwable -> message.getChannel().sendMessage(message.getAuthor().getAsMention() + ", vérifez que vos mp sont ouverts pour pouvoir démarrer la création d'un sondage.").queue())
        );

    }
}
