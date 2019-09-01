package fr.alkadev.luminis.database.task;

import fr.alkadev.luminis.database.DatabaseManager;

import java.util.TimerTask;

public class DatabaseTask extends TimerTask {

    private final DatabaseManager databaseManager;

    public DatabaseTask(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void run() {
        this.databaseManager.save();
        this.databaseManager.load();
    }

}
