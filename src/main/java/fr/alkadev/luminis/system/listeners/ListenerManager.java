package fr.alkadev.luminis.system.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public abstract class ListenerManager {

    abstract List<ListenerAdapter> getListeners();

    public void saveListeners(JDA jda) {
        this.getListeners().forEach(jda::addEventListener);
    }

}
