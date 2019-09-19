package fr.alkadev.luminis.addons.system;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.system.commands.AboutCommand;
import fr.alkadev.luminis.addons.system.commands.RemindCommand;
import fr.alkadev.luminis.addons.system.commands.UserInfosCommand;
import fr.alkadev.luminis.core.database.DatabaseManager;
import fr.alkadev.luminis.addons.system.listeners.GuildJoinListener;
import fr.alkadev.luminis.addons.system.listeners.ReadyListener;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;

public class SystemAddon implements Addon {

    private final DatabaseManager databaseManager;
    private final LuminisManagers luminisManagers;

    public SystemAddon(DatabaseManager databaseManager, LuminisManagers luminisManagers) {
        this.databaseManager = databaseManager;
        this.luminisManagers = luminisManagers;
    }

    @Override
    public void registerCommand(CommandClientBuilder clientBuilder) {

        Arrays.asList(
                new RemindCommand(),
                new AboutCommand(),
                new UserInfosCommand()
        ).forEach(clientBuilder::addCommand);

    }

    @Override
    public void registerListeners(JDA jda) {

        jda.addEventListener(
                new ReadyListener(this.databaseManager),
                new GuildJoinListener(this.luminisManagers)
        );

    }

    @Override
    public String getName() {
        return "Core";
    }

}
