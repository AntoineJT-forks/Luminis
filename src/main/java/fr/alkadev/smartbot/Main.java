package fr.alkadev.smartbot;

import fr.alkadev.smartbot.commands.commandsmanagers.SmartBotCommandsManager;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.events.ListenerManager;
import fr.alkadev.smartbot.events.SmartBotListenerBuilder;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.utils.configuration.Configuration;
import fr.alkadev.smartbot.utils.configuration.ConfigurationLoader;
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

        SmartBot smartBot = new SmartBot(databaseManager);
        smartBot.start(configuration.token, listenerManager);

        while (!SCANNER.nextLine().equalsIgnoreCase("stop")) {
            LOGGER.error("write \"stop\" to stop the bot");
        }

        smartBot.stop();
    }

    private static void load() {
        SmartBotManagers smartBotManagers = new SmartBotManagers();

        configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        databaseManager = new DatabaseManager(configuration, smartBotManagers);
        listenerManager = SmartBotListenerBuilder
                .aSmartBotListener()
                .withDatabaseManager(databaseManager)
                .withSmartBotManagers(smartBotManagers)
                .withCommandsManager(new SmartBotCommandsManager(smartBotManagers))
                .build();
    }

}
