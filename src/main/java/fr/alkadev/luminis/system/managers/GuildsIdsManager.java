package fr.alkadev.luminis.system.managers;

import fr.alkadev.luminis.database.DatabaseSaver;
import org.jooq.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

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
    public void save(DSLContext context) {

        context.truncate(table("guilds_ids")).execute();

        for (Map.Entry<Long, Integer> entry : this.guildsIds.entrySet()) {

            context.insertInto(table("guilds_ids"))
                    .columns(field("guild_id"), field("id"))
                    .values(String.valueOf(entry.getKey()), entry.getValue())
                    .execute();

        }

    }

    @Override
    public void load(DSLContext context) {

        this.guildsIds.clear();

        Result<Record2<Object, Object>> result = context
                .select(field("id"), field("guild_id"))
                .from(table("guilds_ids"))
                .fetch();

        for (Record record : result) {

            int id = record.getValue("id", Integer.class);
            long guildId = record.getValue("guild_id", Long.class);
            this.index = id;

            this.add(guildId, 0);

        }

    }

}
