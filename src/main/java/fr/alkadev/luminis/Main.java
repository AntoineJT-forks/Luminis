package fr.alkadev.luminis;

import fr.alkadev.luminis.commands.commandsmanagers.LuminisCommandsManager;
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

    public static void main(String[] args) {

        load();

        Luminis luminis = new Luminis(databaseManager);
        luminis.start(configuration.token, listenerManager);

        while (!SCANNER.nextLine().equalsIgnoreCase("stop")) {
            LOGGER.error("write \"stop\" to stop the bot");
        }

        luminis.stop();
    }

    private static void load() {
        LuminisManagers luminisManagers = new LuminisManagers();

        configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        databaseManager = new DatabaseManager(configuration, luminisManagers);
        listenerManager = LuminisListenerBuilder
                .aSmartBotListener()
                .withDatabaseManager(databaseManager)
                .withSmartBotManagers(luminisManagers)
                .withCommandsManager(new LuminisCommandsManager(luminisManagers))
                .build();
    }

}
