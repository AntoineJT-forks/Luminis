package fr.alkadev.smartbot.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter {

    private FileWriter(){}

    public static void writeFile(File file, String content) {

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