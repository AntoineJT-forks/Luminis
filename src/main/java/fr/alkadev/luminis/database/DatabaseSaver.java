package fr.alkadev.luminis.database;

import org.jooq.DSLContext;

public interface DatabaseSaver {

    void save(DSLContext context);

    void load(DSLContext context);

}
