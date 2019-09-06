package fr.alkadev.luminis;

import fr.alkadev.luminis.commands.LuminisCommandsManager;
import fr.alkadev.luminis.database.DatabaseManager;
import fr.alkadev.luminis.events.ListenerManager;
import fr.alkadev.luminis.events.LuminisListenerBuilder;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import fr.alkadev.luminis.utils.configuration.Configuration;
import fr.alkadev.luminis.utils.configuration.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    private static Configuration configuration;
    private static DatabaseManager databaseManager;
    private static ListenerManager listenerManager;
    private static LuminisCommandsManager commandsManager;

    public static void main(String[] args) {

        load();

        LuminisBot luminisBot = new LuminisBot(databaseManager);
        luminisBot.start(configuration, listenerManager, commandsManager);

        while (!SCANNER.nextLine().equalsIgnoreCase("stop")) {
            LOGGER.error("write \"stop\" to stop the bot");
        }

        luminisBot.stop();
    }

    private static void load() {
        LuminisManagers luminisManagers = new LuminisManagers();

        configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        commandsManager = new LuminisCommandsManager(luminisManagers);
        databaseManager = new DatabaseManager(configuration, luminisManagers);

        listenerManager = LuminisListenerBuilder
                .aSmartBotListener()
                .withDatabaseManager(databaseManager)
                .withSmartBotManagers(luminisManagers)
                .build();
    }

}
