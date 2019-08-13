package fr.alkadev.smartbot.utils.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.alkadev.smartbot.utils.configuration.FileLoader;

import java.io.File;

class Serializer<T> {

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    String serialize(T object) {
        return GSON.toJson(object);
    }

    T deserialize(File file, Class<T> tClass) {
        String fileContent = FileLoader.load(file);
        return GSON.fromJson(fileContent, tClass);
    }

}
