package fr.alkadev.luminis.utils.configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class FileLoader {

    private FileLoader() {
    }

    static String load(File file) {

        String text = "";

        try {
            file.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(file));
            text = read(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;

    }

    private static String read(BufferedReader reader) throws IOException {
        StringBuilder text = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            text.append(line).append("\n");
        }

        return text.toString();
    }

}
