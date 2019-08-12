package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.database.DatabaseManager;
import fr.alkadev.smartbot.database.task.DatabaseTask;
import fr.alkadev.smartbot.events.Listener;
import net.dv8tion.jda.core.events.ReadyEvent;

import java.util.Timer;

public class ReadyListener implements Listener<ReadyEvent> {

    private final DatabaseManager databaseManager;

    public ReadyListener(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public Class<ReadyEvent> getEventClass() {
        return ReadyEvent.class;
    }

    @Override
    public void executeListener(ReadyEvent event) {
        int tenMinutesPeriod = 1_000 * 60 * 10;
        new Thread(() -> new Timer().schedule(new DatabaseTask(this.databaseManager), 0, tenMinutesPeriod)).start();
    }

}
