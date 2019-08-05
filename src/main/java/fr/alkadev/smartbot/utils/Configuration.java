package fr.alkadev.smartbot.utils;

import java.io.File;

public class Configuration {

    public static final File CONFIGURATION_FILE = new File("configuration.json");

    private String token = "";
    private char prefix = ' ';

    public String getToken() {
        return this.token;
    }

    public char getPrefix() {
        return prefix;
    }

}

