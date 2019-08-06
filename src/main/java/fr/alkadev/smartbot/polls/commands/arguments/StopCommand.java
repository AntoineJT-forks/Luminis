package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.commands.CommandRestricted;
import fr.alkadev.smartbot.polls.PollsManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

class StopCommand implements CommandRestricted {

    private final PollsManager pollsManager;

    StopCommand(PollsManager pollsManager) {
        this.pollsManager = pollsManager;
    }

    @Override
    public String getCommand() {
        return "stop";
    }

    @Override
    public String getDescription() {
        return "Annuler la création d'un sondage.";
    }

    @Override
    public void execute(Message message, String[] args) {

        message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(this.getMessage(message.getAuthor())).queue());
        this.pollsManager.removePoll(message.getAuthor().getIdLong());

    }

    private String getMessage(User user) {

        String message = "Vous n'avez pas de sondage en cours de création.";

        if (this.pollsManager.hasPoll(user)) {
            message = "Annulation du sondage.";
        }

        return message;
    }

}
