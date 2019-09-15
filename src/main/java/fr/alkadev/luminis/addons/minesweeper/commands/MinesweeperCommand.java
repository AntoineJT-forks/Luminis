package fr.alkadev.luminis.addons.minesweeper.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.addons.minesweeper.model.MinesweeperBuilder;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.core.commands.LuminisCommand;

import java.util.List;
import java.util.stream.Collectors;

@Author("Ka'Eios")
@CommandInfo(name = "minesweeper", description = "start a minesweeper game")
public class MinesweeperCommand extends LuminisCommand {

    public MinesweeperCommand(List<Command> arguments) {
        this.name = "minesweeper";
        this.children = arguments.toArray(new Command[]{});
        this.help = "Créer une grille de démineur.\n" + super.getChildrenHelp();
        this.category = CommandCategory.GAME.category;
        this.arguments = "[rien - " + arguments.stream().map(Command::getName).collect(Collectors.joining(" - ")) + "]";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {

        MinesweeperBuilder minesweeperBuilder = new MinesweeperBuilder(MinesweeperDifficulty.MEDIUM);
        minesweeperBuilder.buildGrid();

        event.reply(minesweeperBuilder.buildMessage());
    }

}
