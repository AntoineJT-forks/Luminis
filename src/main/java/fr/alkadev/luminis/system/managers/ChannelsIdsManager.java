package fr.alkadev.luminis.system.managers;

import fr.alkadev.luminis.database.DatabaseSaver;
import fr.alkadev.luminis.model.GuildChannelsIds;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Result;

import java.util.HashMap;
import java.util.Map;

import static org.jooq.impl.DSL.*;

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
    public void save(DSLContext context) {

        context.truncate(table("channels_ids")).execute();

        for (Map.Entry<Integer, GuildChannelsIds> entry : this.guilds.entrySet()) {
            saveGuildChannelsIds(context, entry);
        }

    }

    private void saveGuildChannelsIds(DSLContext context, Map.Entry<Integer, GuildChannelsIds> entry) {

        for (Map.Entry<String, Long> channelsIds : entry.getValue().getChannelsIds().entrySet()) {

            context.insertInto(table("channels_ids"))
                    .columns(field("guild_id"), field("channel_name"), field("channel_id"))
                    .values(entry.getKey(), channelsIds.getKey(), String.valueOf(channelsIds.getValue()))
                    .onConflictOnConstraint(constraint("channels_ids_pk"))
                    .doUpdate()
                    .set(field("channel_id"), String.valueOf(channelsIds.getValue()))
                    .execute();

        }

    }

    @Override
    public void load(DSLContext context) {

        this.guilds.clear();

        Result<Record3<Object, Object, Object>> result = context
                .select(field("guild_id"), field("channel_name"), field("channel_id"))
                .from(table("channels_ids"))
                .fetch();

        for (Record record : result) {

            int guildId = record.getValue("guild_id", Integer.class);
            String channelName = record.getValue("channel_name", String.class);
            long channelId = record.getValue("channel_id", Long.class);

            if (!this.isPresent(guildId)) this.add(guildId, new GuildChannelsIds());
            this.get(guildId).put(channelName, channelId);

        }

    }

}
