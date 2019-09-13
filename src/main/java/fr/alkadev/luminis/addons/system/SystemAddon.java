package fr.alkadev.luminis.addons.system;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.system.commands.AboutCommand;
import fr.alkadev.luminis.addons.system.commands.RemindCommand;
import fr.alkadev.luminis.addons.system.commands.UserInfosCommand;
import fr.alkadev.luminis.system.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;

public class SystemAddon implements Addon {

    @Override
    public boolean start(LuminisManagers luminisManagers) {
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

    @Override
    public void registerCommand(CommandClientBuilder clientBuilder) {

        Arrays.asList(
                new RemindCommand(),
                new AboutCommand(),
                new UserInfosCommand()
        ).forEach(clientBuilder::addCommand);

    }

    @Override
    public void registerListeners(JDA jda) {

    }

    @Override
    public String getAddonName() {
        return "System";
    }

}
