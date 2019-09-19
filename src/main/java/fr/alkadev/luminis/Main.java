package fr.alkadev.luminis;

import fr.alkadev.luminis.addons.AddonsManager;
import fr.alkadev.luminis.addons.LuminisAddonsManager;
import fr.alkadev.luminis.core.database.DatabaseManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;
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
    private static AddonsManager addonsManager;
    private static LuminisManagers luminisManagers;

    public static void main(String[] args) {

        load();

        LuminisBot luminisBot = new LuminisBot(databaseManager, addonsManager);

        luminisBot.buildClient(configuration, luminisManagers);
        luminisBot.start(configuration);

        while (!SCANNER.nextLine().equalsIgnoreCase("stop")) {
            LOGGER.error("write \"stop\" to stop the bot");
        }

        luminisBot.stop();

    }

    private static void load() {
        luminisManagers = new LuminisManagers();
        configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        databaseManager = new DatabaseManager(configuration, luminisManagers);
        addonsManager = new LuminisAddonsManager(databaseManager, luminisManagers);
    }

}
