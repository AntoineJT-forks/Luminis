package fr.alkadev.luminis.core.task;

import fr.alkadev.luminis.core.database.DatabaseManager;

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
