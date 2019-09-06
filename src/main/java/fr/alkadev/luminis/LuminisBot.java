package fr.alkadev.luminis;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.commands.CommandsManager;
import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.events.ListenerManager;
import fr.alkadev.luminis.utils.configuration.Configuration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class LuminisBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(LuminisBot.class);

    private final DatabaseManager databaseManager;
    private JDA jda;

    LuminisBot(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    void start(Configuration configuration, ListenerManager listenerManager, CommandsManager commandsManager) {

        try {

            CommandClientBuilder clientBuilder = new CommandClientBuilder()
                    .setPrefix(configuration.prefix)
                    .setActivity(Activity.of(Activity.ActivityType.DEFAULT, configuration.prefix + "help"))
                    .setOwnerId(configuration.ownerId);

            commandsManager.registerCommands(clientBuilder);

            this.jda = new JDABuilder(configuration.token)
                    .addEventListeners(clientBuilder.build())
                    .build();

            listenerManager.saveListeners(jda);
            this.databaseManager.load();

            LOGGER.info("Bot connected");

        } catch (LoginException ignored) {
            LOGGER.error("Failed to connect the bot");
            System.exit(0);
        }

    }

    void stop() {
        this.databaseManager.save();
        this.jda.shutdown();
        LOGGER.info("Bot disconnected");
        System.exit(0);
    }

}
