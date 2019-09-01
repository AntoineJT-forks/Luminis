package fr.alkadev.luminis.database;

class DatabaseBuilder {

    String host;
    String userName;
    String password;
    String databaseName;

    private DatabaseBuilder() {}

    static DatabaseBuilder aDatabaseBuilder() {
        return new DatabaseBuilder();
    }

    DatabaseBuilder withHost(String host) {
        this.host = host;
        return this;
    }

    DatabaseBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    DatabaseBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    DatabaseBuilder withDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    DatabaseConnection build() {
        return new DatabaseConnection(this);
    }

}