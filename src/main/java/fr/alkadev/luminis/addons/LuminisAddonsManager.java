package fr.alkadev.luminis.addons;

import fr.alkadev.luminis.addons.polls.PollsAddon;
import fr.alkadev.luminis.addons.system.SystemAddon;

import java.util.Arrays;
import java.util.List;

public class LuminisAddonsManager extends AddonsManager {

    @Override
    List<Addon> getAddons() {

        return Arrays.asList(
                new PollsAddon(),
                new SystemAddon()
        );

    }

}
