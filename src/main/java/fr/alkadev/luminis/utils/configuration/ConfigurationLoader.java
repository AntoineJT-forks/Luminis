package fr.alkadev.luminis.utils.configuration;

import java.io.File;

public class ConfigurationLoader {

    public static Configuration loadFrom(File configurationFile) {
        Configuration configuration = new Serializer<Configuration>().deserialize(configurationFile, Configuration.class);

        if (configuration == null) {
            configuration = new Configuration();
            FileWriter.writeFile(configurationFile, new Serializer<Configuration>().serialize(configuration));
        }

        return configuration;
    }

}
