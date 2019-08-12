package fr.alkadev.smartbot.database;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final List<DatabaseSaver> savers;

    public DatabaseManager() {
        savers = new ArrayList<>();
    }

    public void addSavers(List<DatabaseSaver> savers) {
        this.savers.addAll(savers);
    }

    public void addSaver(DatabaseSaver databaseSaver) {
        this.savers.add(databaseSaver);
    }

    public void load() {
        this.savers.forEach(DatabaseSaver::load);
    }

    public void save() {
        this.savers.forEach(DatabaseSaver::save);
    }

}
