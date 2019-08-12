package fr.alkadev.smartbot.utils;

import java.io.File;

public class Configuration {

    private static final File CONFIGURATION_FILE = new File("configuration.json");

    public String token = "";
    public char prefix = ' ';
    public String host;
    public String userName;
    public String password;
    public String databaseName;

    public static Configuration loadConfiguration() {
        Configuration configuration = new Serializer<Configuration>().deserialize(CONFIGURATION_FILE, Configuration.class);

        if (configuration == null) {
            configuration = new Configuration();
            String json = new Serializer<Configuration>().serialize(configuration);
            FileWriter.writeFile(Configuration.CONFIGURATION_FILE, json);
        }

        return configuration;
    }

}

