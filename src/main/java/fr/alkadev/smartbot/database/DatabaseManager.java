package fr.alkadev.smartbot.database;

import fr.alkadev.smartbot.system.managers.SmartBotManagers;
import fr.alkadev.smartbot.utils.configuration.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private final SmartBotManagers smartBotManagers;
    private final DatabaseConnection databaseConnection;

    public DatabaseManager(Configuration configuration, SmartBotManagers smartBotManagers) {
        this.smartBotManagers = smartBotManagers;
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

        for (DatabaseSaver databaseSaver : this.smartBotManagers.getSavers())
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

        for (DatabaseSaver databaseSaver : this.smartBotManagers.getSavers())
            databaseSaver.save(connection);

    }

}
