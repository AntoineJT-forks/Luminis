package fr.alkadev.luminis;

import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.events.ListenerManager;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class Luminis {

    private static final Logger LOGGER = LoggerFactory.getLogger(Luminis.class);

    private final DatabaseManager databaseManager;
    private JDA jda;

    Luminis(DatabaseManager databaseManager) {
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
