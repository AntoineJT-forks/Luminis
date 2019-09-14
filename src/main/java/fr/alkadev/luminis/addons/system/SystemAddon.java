package fr.alkadev.luminis.addons.system;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.system.commands.AboutCommand;
import fr.alkadev.luminis.addons.system.commands.RemindCommand;
import fr.alkadev.luminis.addons.system.commands.UserInfosCommand;

import java.util.Arrays;

public class SystemAddon implements Addon {

    @Override
    public void registerCommand(CommandClientBuilder clientBuilder) {

        Arrays.asList(
                new RemindCommand(),
                new AboutCommand(),
                new UserInfosCommand()
        ).forEach(clientBuilder::addCommand);

    }

    @Override
    public String getName() {
        return "Core";
    }

}
