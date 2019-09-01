package fr.alkadev.luminis.events;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.List;

public abstract class ListenerManager {

    abstract List<ListenerAdapter> getListeners();

    public void saveListeners(JDA jda) {
        this.getListeners().forEach(jda::addEventListener);
    }

}
