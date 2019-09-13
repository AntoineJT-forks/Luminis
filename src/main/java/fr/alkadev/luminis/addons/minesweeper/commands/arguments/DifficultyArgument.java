package fr.alkadev.luminis.addons.minesweeper.commands.arguments;

import com.jagrosh.jdautilities.command.CommandEvent;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperBuilder;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.system.commands.LuminisCommand;

public class DifficultyArgument extends LuminisCommand {

    public DifficultyArgument() {
        this.name = "difficulty";
        this.help = "Créer une partie personalisée (easy, medium, hard, expert).";
        this.category = CommandCategory.GAME.category;
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {
        if(args.length == 1){
            try{
                final MinesweeperDifficulty difficulty = MinesweeperDifficulty.valueOf(args[0].toUpperCase());
                event.getChannel().sendMessage(new MinesweeperBuilder().withDifficulty(difficulty).getMinesweeperMessage()).queue();
            } catch (IllegalArgumentException expected){
                event.replyInDm("`" + event.getMessage().getContentRaw().toCharArray()[0] + getName() + "` - " + this.getHelp());
            }
        } else {
            event.replyInDm("`" + event.getMessage().getContentRaw().toCharArray()[0] + getName() + "` - " + this.getHelp());
        }
    }

}
