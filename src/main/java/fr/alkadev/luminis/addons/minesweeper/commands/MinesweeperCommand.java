package fr.alkadev.luminis.addons.minesweeper.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperBuilder;
import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.system.commands.CommandCategory;
import fr.alkadev.luminis.system.commands.LuminisCommand;

import java.util.List;
import java.util.stream.Collectors;

public class MinesweeperCommand extends LuminisCommand {

    public MinesweeperCommand(List<Command> arguments) {
        this.name = "minesweeper";
        this.help = "Créer une grille de démineur";
        this.category = CommandCategory.GAME.category;
        this.arguments = "[" + arguments.stream().map(Command::getName).collect(Collectors.joining(" - ")) + "]";
        this.children = arguments.toArray(new Command[]{});
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event, String[] args) {
        if(args.length == 0){
            event.getChannel().sendMessage(new MinesweeperBuilder().withDifficulty(MinesweeperDifficulty.MEDIUM).getMinesweeperMessage()).queue();
        } else {
            event.replyInDm("`" + event.getMessage().getContentRaw().toCharArray()[0] + getName() + "` - " + this.getHelp());
        }
    }

}
