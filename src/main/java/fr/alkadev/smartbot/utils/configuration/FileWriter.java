package fr.alkadev.smartbot.utils.configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

class FileWriter {

    private FileWriter(){}

    static void writeFile(File file, String content) {

        BufferedWriter bufferedWriter;

        try {
            file.createNewFile();

            bufferedWriter = new BufferedWriter(new java.io.FileWriter(file));
            bufferedWriter.write(content);
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}