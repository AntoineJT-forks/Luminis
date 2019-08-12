package fr.alkadev.smartbot;

import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.events.SmartBotListener;
import fr.alkadev.smartbot.utils.Configuration;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class SmartBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartBot.class);

    private final Configuration configuration;
    private final DatabaseManager databaseManager;
    private JDA jda;

    SmartBot() {
        this.configuration = Configuration.loadConfiguration();
        this.databaseManager = new DatabaseManager(this.configuration);
    }

    void start() {
        try {

            this.jda = new JDABuilder(this.configuration.token)
                    .addEventListener(new SmartBotListener(this.configuration.prefix, this.databaseManager))
                    .build();

            LOGGER.info("Bot connected");

        } catch (LoginException ignored) {
            LOGGER.error("Failed to connect the bot");
            System.exit(0);
        }
    }

    void stop() {
        this.jda.shutdown();
        LOGGER.info("Bot disconnected");
        System.exit(0);
    }

}
