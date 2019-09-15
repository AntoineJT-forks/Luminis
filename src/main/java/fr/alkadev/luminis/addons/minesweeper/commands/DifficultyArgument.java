package fr.alkadev.luminis.addons.minesweeper.commands;

import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;
import fr.alkadev.luminis.addons.minesweeper.model.MinesweeperBuilder;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.core.commands.CommandCategory;
import fr.alkadev.luminis.core.commands.LuminisCommand;

@Author("Ka'Eios")
@CommandInfo(name = "difficulty", description = "start a minesweeper game with a difficulty given by user")
public class DifficultyArgument extends LuminisCommand {

    public DifficultyArgument() {
        this.name = "difficulty";
        this.help = "Créer une partie personalisée.";
        this.arguments = "[easy - medium - hard - expert]";
        this.category = CommandCategory.GAME.category;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {

        MinesweeperDifficulty difficulty = getDifficulty(args);

        if (difficulty != null) {
            MinesweeperBuilder minesweeperBuilder = new MinesweeperBuilder(difficulty);
            minesweeperBuilder.buildGrid();

            event.reply(minesweeperBuilder.buildMessage());
            return;
        }

        event.reply(this.getHelp() + " " + this.getArguments());
    }

    private MinesweeperDifficulty getDifficulty(String[] args) {

        try {
            return MinesweeperDifficulty.valueOf(args[0].toUpperCase());
        } catch (Exception expected) {
            return null;
        }

    }

}
