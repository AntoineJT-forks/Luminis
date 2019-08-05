package fr.alkadev.smartbot.events;

import net.dv8tion.jda.core.events.ReadyEvent;

public class ReadyListener implements Listener<ReadyEvent> {

    @Override
    public Class<ReadyEvent> getEventClass() {
        return ReadyEvent.class;
    }

    @Override
    public void executeListener(ReadyEvent event) {

        System.out.println("Bot ready");

    }

}
