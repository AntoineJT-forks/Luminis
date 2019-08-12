package fr.alkadev.smartbot.database;

import fr.alkadev.smartbot.utils.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final List<DatabaseSaver> savers;
    private final DatabaseConnection databaseConnection;

    public DatabaseManager(Configuration configuration) {
        this.savers = new ArrayList<>();
        this.databaseConnection = DatabaseBuilder
                .aDatabaseBuilder()
                .withHost(configuration.host)
                .withUserName(configuration.userName)
                .withPassword(configuration.password)
                .withDatabaseName(configuration.databaseName)
                .build();
    }

    public void addSavers(List<DatabaseSaver> savers) {
        this.savers.addAll(savers);
    }

    public void addSaver(DatabaseSaver databaseSaver) {
        this.savers.add(databaseSaver);
    }

    public void load() {
        this.savers.forEach(DatabaseSaver::load);
    }

    public void save() {
        this.savers.forEach(DatabaseSaver::save);
    }

    public Connection getConnection() throws SQLException {
        return this.databaseConnection.getConnection();
    }

}
