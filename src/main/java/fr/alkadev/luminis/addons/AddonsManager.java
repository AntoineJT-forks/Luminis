package fr.alkadev.luminis.addons;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class AddonsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddonsManager.class);

    private final List<Addon> addons;

    AddonsManager() {
        addons = this.getAddons();
    }

    abstract List<Addon> getAddons();

    public void start(LuminisManagers luminisManagers) {

        this.addons.forEach(addon -> this.startAddon(addon, luminisManagers));

    }

    private void startAddon(Addon addon, LuminisManagers luminisManagers) {

        LOGGER.info("ENABLING " + addon.getName() + " addon");

        if (!addon.start(luminisManagers)) {
            LOGGER.error("FAILED to load " + addon.getName() + " addon");
        }

    }

    public void registerCommands(CommandClientBuilder commandClientBuilder) {

        this.addons.forEach(addon -> addon.registerCommand(commandClientBuilder));

    }

    public void registerListeners(JDA jda) {

        this.addons.forEach(addon -> addon.registerListeners(jda));

    }

    public void stop() {
        this.addons.forEach(this::stopAddon);
    }

    private void stopAddon(Addon addon) {

        LOGGER.info("DISABLING " + addon.getName() + " addon");

        if (!addon.stop()) {
            LOGGER.error("FAILED to disable " + addon.getName() + " addon");
        }

    }

}
