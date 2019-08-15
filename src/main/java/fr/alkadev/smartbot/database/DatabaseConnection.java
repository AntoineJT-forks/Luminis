package fr.alkadev.smartbot.database;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseConnection {

    private final HikariDataSource dataSource;

    DatabaseConnection(DatabaseBuilder builder) {
        this.dataSource = new HikariDataSource();
        setUpHikariCP(builder);
    }

    private void setUpHikariCP(DatabaseBuilder builder) {
        this.dataSource.setJdbcUrl("jdbc:postgresql://" + builder.host + ":5432/" + builder.databaseName);
        this.dataSource.setUsername(builder.userName);
        this.dataSource.setPassword(builder.password);
        this.dataSource.addDataSourceProperty("autoReconnect", true);
        this.dataSource.addDataSourceProperty("tcpKeepAlive", true);
        this.dataSource.addDataSourceProperty("serverTimezone", "Europe/Paris");
        this.dataSource.setMaximumPoolSize(15);
        this.dataSource.setMinimumIdle(0);
        this.dataSource.setIdleTimeout(10000);
    }

    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
