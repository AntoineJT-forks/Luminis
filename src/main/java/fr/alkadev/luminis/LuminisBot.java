package fr.alkadev.luminis;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.AddonsManager;
import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import fr.alkadev.luminis.utils.configuration.Configuration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;

class LuminisBot {

    private static final Logger LOGGER = LoggerFactory.getLogger(LuminisBot.class);

    private final DatabaseManager databaseManager;
    private final AddonsManager addonsManager;
    private JDA jda;
    private CommandClientBuilder clientBuilder;

    LuminisBot(DatabaseManager databaseManager, AddonsManager addonsManager) {
        this.databaseManager = databaseManager;
        this.addonsManager = addonsManager;
    }

    void buildClient(Configuration configuration, LuminisManagers luminisManagers) {
        this.clientBuilder = new CommandClientBuilder()
                .setPrefix(configuration.prefix)
                .setActivity(Activity.of(Activity.ActivityType.DEFAULT, configuration.prefix + "help"))
                .setOwnerId(configuration.ownerId)
                .setHelpWord("help")
                .setStatus(OnlineStatus.ONLINE)
                .setCoOwnerIds(configuration.coOwnerIds);

        this.addonsManager.start(luminisManagers);
        this.addonsManager.registerCommands(clientBuilder);
    }

    void start(Configuration configuration) {

        try {

            this.jda = new JDABuilder(configuration.token)
                    .addEventListeners(clientBuilder.build())
                    .build();

            addonsManager.registerListeners(jda);
            this.databaseManager.load();

            LOGGER.info("Bot connected");

        } catch (LoginException ignored) {
            LOGGER.error("Failed to connect the bot");
            System.exit(0);
        }

    }

    void stop() {
        this.addonsManager.stop();
        this.databaseManager.save();
        this.jda.shutdown();
        LOGGER.info("Bot disconnected");
        System.exit(0);
    }

}
