package fr.alkadev.smartbot.database;

public class DatabaseBuilder {

    public String host;
    public String userName;
    public String password;
    public String databaseName;

    private DatabaseBuilder() {}

    public static DatabaseBuilder aDatabaseBuilder() {
        return new DatabaseBuilder();
    }

    public DatabaseBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    public DatabaseBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public DatabaseBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public DatabaseBuilder withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public DatabaseConnection build() {
        return new DatabaseConnection(this);
    }

}
