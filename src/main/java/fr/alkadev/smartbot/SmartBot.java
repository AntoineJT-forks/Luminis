package fr.alkadev.smartbot;

import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.events.ListenersManager;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.events.SmartBotListener;
import fr.alkadev.smartbot.utils.configuration.Configuration;
import fr.alkadev.smartbot.utils.configuration.ConfigurationLoader;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;

class SmartBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartBot.class);

    private final Configuration configuration;
    private final SmartBotManagers smartBotManagers;
    private final DatabaseManager databaseManager;
    private JDA jda;

    SmartBot() {
        this.configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        this.smartBotManagers = new SmartBotManagers();
        this.databaseManager = new DatabaseManager(this.configuration, this.smartBotManagers);
    }

    void start() {
        try {

            this.databaseManager.load();

            this.jda = new JDABuilder(this.configuration.token)
                    .addEventListener(new SmartBotListener(new ListenersManager(this.configuration.prefix, databaseManager, smartBotManagers)))
                    .build();

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
