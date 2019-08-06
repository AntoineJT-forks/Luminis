package fr.alkadev.smartbot.system.reminds;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Remind {

    private User user;
    private TextChannel channel;
    private long time;
    private String message;

    private final String ERROR_SEND_PRIVATE_CHANNEL = "Vous devez activer la réception des messages privés afin de recevoir vos rappels. ";

    Remind(RemindBuilder remindBuilder){
        this.user = remindBuilder.user;
        this.channel = remindBuilder.channel;
        this.time = remindBuilder.time;;
        this.message = remindBuilder.message;;

    }

    public void sendRemind(){

        Consumer<Throwable> throwableConsumer = throwable -> channel.sendMessage(ERROR_SEND_PRIVATE_CHANNEL + user.getAsMention()).queue();
        Consumer<PrivateChannel> privateChannelConsumer = privateChannel -> privateChannel.sendMessage(message).queue(message1 -> {}, throwableConsumer);

        user.openPrivateChannel().queueAfter(time, TimeUnit.MILLISECONDS, privateChannelConsumer);

    }

}
