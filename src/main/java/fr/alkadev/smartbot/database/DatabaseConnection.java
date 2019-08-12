package fr.alkadev.smartbot.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnection {

    private HikariDataSource dataSource;

    DatabaseConnection(DatabaseBuilder builder) {
        this.dataSource = new HikariDataSource();
        this.dataSource.setJdbcUrl("jdbc:mysql://" + builder.host + ":3306/" + builder.databaseName);
        this.dataSource.setUsername(builder.userName);
        this.dataSource.setPassword(builder.password);
        this.dataSource.addDataSourceProperty("autoReconnect", true);
        this.dataSource.addDataSourceProperty("tcpKeepAlive", true);
        this.dataSource.addDataSourceProperty("serverTimezone", "Europe/Paris");
        this.dataSource.setMaximumPoolSize(15);
        this.dataSource.setMinimumIdle(0);
        this.dataSource.setIdleTimeout(1);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
