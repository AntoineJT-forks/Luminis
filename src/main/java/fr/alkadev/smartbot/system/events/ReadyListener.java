package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.database.task.DatabaseTask;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Timer;

public class ReadyListener extends ListenerAdapter {

    private final DatabaseManager databaseManager;

    public ReadyListener(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void onReady(ReadyEvent event) {
        int tenMinutesPeriod = 1_000 * 60 * 10;
        new Thread(() -> new Timer().schedule(new DatabaseTask(this.databaseManager), 0, 3000)).start();
    }

}
