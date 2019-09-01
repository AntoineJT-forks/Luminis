package fr.alkadev.luminis.system.managers;

import fr.alkadev.luminis.database.DatabaseSaver;
import fr.alkadev.luminis.system.model.GuildChannelsIds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChannelsIdsManager implements LuminisManager<GuildChannelsIds, Integer>, DatabaseSaver {

    private final Map<Integer, GuildChannelsIds> guilds = new HashMap<>();

    @Override
    public boolean isPresent(Integer guildId) {
        return this.guilds.containsKey(guildId);
    }

    @Override
    public GuildChannelsIds get(Integer guildId) {
        return this.guilds.get(guildId);
    }

    @Override
    public void add(Integer guildId, GuildChannelsIds guildChannelsIds) {
        this.guilds.put(guildId, guildChannelsIds);
    }

    @Override
    public void remove(Integer guildId) {
        this.guilds.remove(guildId);
    }

    @Override
    public void save(Connection connection) throws SQLException {

        connection.prepareStatement("TRUNCATE TABLE channels_ids").execute();

        for (Map.Entry<Integer, GuildChannelsIds> entry : this.guilds.entrySet()) {
            saveGuildChannelsIds(connection, entry);
        }

    }

    private void saveGuildChannelsIds(Connection connection, Map.Entry<Integer, GuildChannelsIds> entry) throws SQLException {

        for (Map.Entry<String, Long> channelsIds : entry.getValue().getChannelsIds().entrySet()) {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO channels_ids(guild_id, channel_name, channel_id) VALUES(?, ?, ?) ON CONFLICT ON CONSTRAINT channels_ids_pk  DO UPDATE SET channel_id = ?");
            preparedStatement.setInt(1, entry.getKey());
            preparedStatement.setString(2, channelsIds.getKey());
            preparedStatement.setString(3, String.valueOf(channelsIds.getValue()));

            preparedStatement.setString(4, String.valueOf(channelsIds.getValue()));

            preparedStatement.execute();

        }

    }

    @Override
    public void load(Connection connection) throws SQLException {

        this.guilds.clear();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from channels_ids");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            int guildId = resultSet.getInt("guild_id");
            String channelName = resultSet.getString("channel_name");
            long channelId = Long.parseLong(resultSet.getString("channel_id"));

            if (!this.isPresent(guildId)) this.add(guildId, new GuildChannelsIds());
            this.get(guildId).put(channelName, channelId);

        }

    }

}
