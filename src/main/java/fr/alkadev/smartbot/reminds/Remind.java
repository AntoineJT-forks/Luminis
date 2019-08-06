package fr.alkadev.smartbot.reminds;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

class Remind {

    private final String ERROR_SEND_PRIVATE_CHANNEL = "Vous devez activer la réception des messages privés afin de recevoir vos rappels. ";

    private final User user;
    private final TextChannel channel;
    private final long time;
    private final String message;

    Remind(RemindBuilder builder) {
        this.user = builder.user;
        this.channel = builder.channel;
        this.time = builder.time;
        this.message = builder.message;
    }

    void send() {

        Consumer<Throwable> throwableConsumer = throwable -> channel.sendMessage(ERROR_SEND_PRIVATE_CHANNEL + user.getAsMention()).queue();
        Consumer<PrivateChannel> privateChannelConsumer = privateChannel -> privateChannel.sendMessage(message).queue(message1 -> {}, throwableConsumer);

        user.openPrivateChannel().queueAfter(time, TimeUnit.MILLISECONDS, privateChannelConsumer);

    }

}
