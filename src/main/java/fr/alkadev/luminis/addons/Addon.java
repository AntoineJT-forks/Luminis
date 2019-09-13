package fr.alkadev.luminis.addons;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;

public interface Addon {

    boolean start(LuminisManagers luminisManagers);

    boolean stop();

    void registerCommand(CommandClientBuilder clientBuilder);

    void registerListeners(JDA jda);

    String getAddonName();

}
