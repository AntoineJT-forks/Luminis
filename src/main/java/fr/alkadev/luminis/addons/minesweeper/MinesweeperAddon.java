package fr.alkadev.luminis.addons.minesweeper;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.minesweeper.commands.MinesweeperCommand;
import fr.alkadev.luminis.addons.minesweeper.commands.arguments.DifficultyArgument;
import fr.alkadev.luminis.core.managers.LuminisManagers;
import net.dv8tion.jda.api.JDA;

import java.util.Collections;
import java.util.List;

public class MinesweeperAddon implements Addon {

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

        List<Command> arguments = Collections.singletonList(
                new DifficultyArgument()
        );

        clientBuilder.addCommand(new MinesweeperCommand(arguments));
        arguments.forEach(clientBuilder::addCommand);
    }

    @Override
    public void registerListeners(JDA jda) {

    }

    @Override
    public String getAddonName() {
        return "Minesweeper";
    }

}
