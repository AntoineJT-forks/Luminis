package fr.alkadev.smartbot;

import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.events.ListenerManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class SmartBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartBot.class);

    private final DatabaseManager databaseManager;
    private JDA jda;

    SmartBot(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    void start(String token, ListenerManager listenerManager) {
        try {

            this.databaseManager.load();

            this.jda = new JDABuilder(token)
                    .build();

            listenerManager.saveListeners(jda);

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
