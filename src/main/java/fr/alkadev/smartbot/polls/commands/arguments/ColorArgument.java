package fr.alkadev.smartbot.polls.commands.arguments;

import fr.alkadev.smartbot.polls.PollColor;
import fr.alkadev.smartbot.polls.PollsManager;
import fr.alkadev.smartbot.polls.commands.PollCommandArgument;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

import java.util.Arrays;

public class ColorArgument extends PollCommandArgument {

    public ColorArgument(PollsManager pollsManager) {
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

    @Override
    public void execute(Message message, String[] args) {

        if (args.length == 0) {
            message.getChannel().sendMessage("Précisez un couleur parmi celles-ci : " + Arrays.toString(PollColor.values()) + ".").queue();
            return;
        }

        message.getAuthor().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(this.getMessage(message.getAuthor())).queue());
        this.pollsManager.setColor(message.getAuthor(), args[0]);

    }

    private String getMessage(User user) {

        String message = "Vous n'avez pas de sondage en cours de création.";

        if (this.pollsManager.hasPoll(user)) {
            message = "La couleur du sondage a bien été changée.";
        }

        return message;
    }

}
