package fr.alkadev.luminis.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseSaver {

    void save(Connection connection) throws SQLException;

    void load(Connection connection) throws SQLException;

}
