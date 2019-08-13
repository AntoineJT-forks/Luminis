package fr.alkadev.smartbot.database;

import java.sql.Connection;

public interface DatabaseSaver {

    void save(Connection connection);

    void load(Connection connection);

}
