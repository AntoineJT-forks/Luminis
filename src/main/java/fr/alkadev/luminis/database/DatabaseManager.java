package fr.alkadev.luminis.database;

import fr.alkadev.luminis.system.managers.LuminisManagers;
import fr.alkadev.luminis.utils.configuration.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private final LuminisManagers luminisManagers;
    private final DatabaseConnection databaseConnection;

    public DatabaseManager(Configuration configuration, LuminisManagers luminisManagers) {
        this.luminisManagers = luminisManagers;
        this.databaseConnection = DatabaseBuilder
                .aDatabaseBuilder()
                .withHost(configuration.host)
                .withUserName(configuration.userName)
                .withPassword(configuration.password)
                .withDatabaseName(configuration.databaseName)
                .build();

    }

    public void load() {
        try (Connection connection = this.databaseConnection.getConnection()) {

            loadManagers(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadManagers(Connection connection) throws SQLException {

        for (DatabaseSaver databaseSaver : this.luminisManagers.getSavers())
            databaseSaver.load(connection);

    }

    public void save() {
        try (Connection connection = this.databaseConnection.getConnection()) {

            saveManagers(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveManagers(Connection connection) throws SQLException {

        for (DatabaseSaver databaseSaver : this.luminisManagers.getSavers())
            databaseSaver.save(connection);

    }

}
