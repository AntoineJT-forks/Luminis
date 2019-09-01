package fr.alkadev.smartbot;

import fr.alkadev.smartbot.commands.commandsmanagers.SmartBotCommandsManager;
import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.events.SmartBotListeners;
import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.utils.configuration.Configuration;
import fr.alkadev.smartbot.utils.configuration.ConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Configuration configuration = ConfigurationLoader.loadFrom(new File("configuration.json"));
        SmartBotManagers smartBotManagers = new SmartBotManagers();
        DatabaseManager databaseManager = new DatabaseManager(configuration, smartBotManagers);

        SmartBot smartBot = new SmartBot(smartBotManagers, databaseManager);

        smartBot.start(configuration.token, new SmartBotListeners(databaseManager, smartBotManagers, new SmartBotCommandsManager(smartBotManagers)));

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equalsIgnoreCase("stop")) {
            LOGGER.error("write \"stop\" to stop the bot");
        }

        smartBot.stop();
    }

}
