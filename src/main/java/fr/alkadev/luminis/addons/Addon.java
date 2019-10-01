package fr.alkadev.luminis.addons;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;

public interface Addon {

    default boolean start(LuminisManagers luminisManagers) {
        return true;
    }

    default boolean stop() {
        return true;
    }

    void registerCommand(CommandClientBuilder clientBuilder);

    default void registerListeners(JDA jda) {
    }

    String getName();

}
