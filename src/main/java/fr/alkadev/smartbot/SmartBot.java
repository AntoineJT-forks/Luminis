package fr.alkadev.smartbot;

import fr.alkadev.smartbot.events.SmartBotListener;
import fr.alkadev.smartbot.utils.Configuration;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class SmartBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartBot.class);

    private JDA jda;

    void start() {
        Configuration configuration = Configuration.loadConfiguration();
        try {

            this.jda = new JDABuilder(configuration.token)
                    .addEventListener(new SmartBotListener(configuration.prefix))
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
