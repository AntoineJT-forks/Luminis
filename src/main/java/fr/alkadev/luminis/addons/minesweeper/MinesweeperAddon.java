package fr.alkadev.luminis.addons.minesweeper;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import fr.alkadev.luminis.addons.Addon;
import fr.alkadev.luminis.addons.minesweeper.commands.MinesweeperCommand;
import fr.alkadev.luminis.addons.minesweeper.commands.arguments.DifficultyArgument;

import java.util.Collections;
import java.util.List;

public class MinesweeperAddon implements Addon {

    @Override
    public void registerCommand(CommandClientBuilder clientBuilder) {

        List<Command> arguments = Collections.singletonList(
                new DifficultyArgument()
        );

        clientBuilder.addCommand(new MinesweeperCommand(arguments));
        arguments.forEach(clientBuilder::addCommand);
    }

    @Override
    public String getName() {
        return "Minesweeper";
    }

}
