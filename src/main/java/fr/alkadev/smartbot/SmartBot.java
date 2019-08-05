package fr.alkadev.smartbot;

import fr.alkadev.smartbot.utils.Configuration;
import fr.alkadev.smartbot.utils.FileWriter;
import fr.alkadev.smartbot.utils.Serializer;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class SmartBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartBot.class);

    private Configuration configuration;

    private JDA jda;

    SmartBot() {

        this.configuration = new Serializer<Configuration>().deserialize(Configuration.CONFIGURATION_FILE, Configuration.class);

        if (this.configuration == null) {
            this.configuration = new Configuration();
            String json = new Serializer<Configuration>().serialize(configuration);
            FileWriter.writeFile(Configuration.CONFIGURATION_FILE, json);
        }

    }

    void start() {
        try {

            this.jda = new JDABuilder(configuration.getToken())
                    .addEventListener()
                    .build();

            LOGGER.info("Bot connected");

        } catch (LoginException ignored) {
            LOGGER.error("Failed to connect the bot");
            System.exit(0);
        }
    }

    void stop() {
        jda.shutdown();
        LOGGER.info("Bot disconnected");
        System.exit(0);
    }

}
