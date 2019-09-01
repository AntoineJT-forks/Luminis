package fr.alkadev.luminis.system.managers;

import fr.alkadev.luminis.database.DatabaseSaver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GuildsIdsManager implements LuminisManager<Integer, Long>, DatabaseSaver {

    private final Map<Long, Integer> guildsIds;
    private int index = 0;

    GuildsIdsManager() {
        guildsIds = new HashMap<>();
    }

    @Override
    public boolean isPresent(Long guildId) {
        return this.guildsIds.containsKey(guildId);
    }

    public Integer get(Long guildId) {
        return this.guildsIds.get(guildId);
    }

    public void add(Long guildId, Integer id) {
        this.guildsIds.put(guildId, this.index++);
    }

    public void remove(Long guildId) {
        this.guildsIds.remove(guildId);
    }

    @Override
    public void save(Connection connection) throws SQLException {

        connection.prepareStatement("TRUNCATE TABLE guilds_ids").execute();

        for (Map.Entry<Long, Integer> entry : this.guildsIds.entrySet()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO guilds_ids(guild_id, id) VALUES(?, ?)");
            preparedStatement.setString(1, String.valueOf(entry.getKey()));
            preparedStatement.setInt(2, entry.getValue());

            preparedStatement.execute();

        }

    }

    @Override
    public void load(Connection connection) throws SQLException {

        this.guildsIds.clear();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM guilds_ids");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            long guildId = Long.parseLong(resultSet.getString("guild_id"));
            this.index = id;

            this.add(guildId, 0);

        }

    }

}
