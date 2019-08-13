package fr.alkadev.smartbot.utils;

import net.dv8tion.jda.core.entities.*;

import java.util.function.Consumer;

public class MessageSender {

    public static void sendPrivateMessage(User user, String message) {
        sendPrivateMessage(user, message, null, null);
    }

    public static void sendPrivateMessage(User user, String message, Consumer<Message> success) {
        sendPrivateMessage(user, message, success, null);
    }

    public static void sendPrivateMessage(User user, String message, Consumer<Message> success, Consumer<Throwable> fail) {
        user.openPrivateChannel().queue(privateChannel -> sendMessage(privateChannel, message, success, fail));
    }

    public static void sendMessage(MessageChannel channel, String message) {
        sendMessage(channel, message, null, null);
    }

    public static void sendMessage(MessageChannel channel, String message, Consumer<Message> success) {
        sendMessage(channel, message, success, null);
    }

    public static void sendMessage(MessageChannel channel, String message, Consumer<Message> success, Consumer<Throwable> fail) {
        channel.sendMessage(message).queue(success, fail);
    }

}
