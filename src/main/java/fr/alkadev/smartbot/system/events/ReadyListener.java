package fr.alkadev.smartbot.system.events;

import fr.alkadev.smartbot.events.Listener;
import net.dv8tion.jda.core.events.ReadyEvent;

public class ReadyListener implements Listener<ReadyEvent> {

    @Override
    public Class<ReadyEvent> getEventClass() {
        return ReadyEvent.class;
    }

    @Override
    public void executeListener(ReadyEvent event) {

    }

}
