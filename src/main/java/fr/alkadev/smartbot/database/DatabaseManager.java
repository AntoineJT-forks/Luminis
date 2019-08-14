package fr.alkadev.smartbot.database;

import fr.alkadev.smartbot.system.managers.GuildsIdsManager;
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

            for (DatabaseSaver databaseSaver : this.smartBotManagers.getSavers()) {
                databaseSaver.load(connection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try (Connection connection = this.databaseConnection.getConnection()) {

            for (DatabaseSaver databaseSaver : this.smartBotManagers.getSavers()) {
                databaseSaver.save(connection);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGuildsIds() {
        try (Connection connection = this.databaseConnection.getConnection()) {

            ((DatabaseSaver) this.smartBotManagers.getManager(GuildsIdsManager.class)).save(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadGuildsIds() {
        try (Connection connection = this.databaseConnection.getConnection()) {

            ((DatabaseSaver) this.smartBotManagers.getManager(GuildsIdsManager.class)).load(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
