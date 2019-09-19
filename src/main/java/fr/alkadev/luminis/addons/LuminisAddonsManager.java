package fr.alkadev.luminis.addons;

import fr.alkadev.luminis.addons.minesweeper.MinesweeperAddon;
import fr.alkadev.luminis.addons.polls.PollsAddon;
import fr.alkadev.luminis.addons.system.SystemAddon;
import fr.alkadev.luminis.core.database.DatabaseManager;
import fr.alkadev.luminis.core.managers.LuminisManagers;

import java.util.Arrays;
import java.util.List;

public class LuminisAddonsManager extends AddonsManager {

    private final List<Addon> addons;

    public LuminisAddonsManager(DatabaseManager databaseManager, LuminisManagers luminisManagers) {
        this.addons = Arrays.asList(
                new PollsAddon(),
                new SystemAddon(databaseManager, luminisManagers),
                new MinesweeperAddon()
        );
    }

    @Override
    List<Addon> getAddons() {

        return this.addons;

    }

}
