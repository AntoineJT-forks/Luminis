package fr.alkadev.luminis.addons.minesweeper.model;

import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.addons.minesweeper.State;

public class MinesweeperBuilder {

    private static final int GRID_SIZE = 13;

    private final State[][] grid;
    private final MinesweeperDifficulty difficulty;

    public MinesweeperBuilder(MinesweeperDifficulty difficulty) {
        grid = new State[MinesweeperBuilder.GRID_SIZE][MinesweeperBuilder.GRID_SIZE];
        this.difficulty = difficulty;
    }

    public void buildGrid(){
        BombPlacer bombPlacer = new BombPlacer(grid);
        NumberPlacer numberPlacer = new NumberPlacer(grid);

        bombPlacer.placeBombs(difficulty);
        numberPlacer.placeNumbers();
    }

    public String buildMessage() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("Démineur : ")
                .append(difficulty.getBombAmount())
                .append(" bombes :bomb:\n\n");

        for (State[] columns : grid) {
            appendColumnString(stringBuilder, columns);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().replaceFirst("\\|\\|:zero:\\|\\|", ":zero:");
    }

    private void appendColumnString(StringBuilder stringBuilder, State[] column) {
        for (State cell : column) {
            stringBuilder.append(cell);
        }
    }

}
