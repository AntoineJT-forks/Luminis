package fr.alkadev.luminis.addons.minesweeper.model;

import fr.alkadev.luminis.addons.minesweeper.MinesweeperDifficulty;
import fr.alkadev.luminis.addons.minesweeper.State;

import java.awt.Point;
import java.util.Random;

class BombPlacer {

    private static final Random RANDOM = new Random();

    private final State[][] grid;

    BombPlacer(State[][] grid) {
        this.grid = grid;
    }

    void placeBombs(MinesweeperDifficulty difficulty) {

        for (int i = 0; i < difficulty.getBombAmount(); i++) {
            Point point = getRandomPosition();
            grid[point.x][point.y] = State.BOMB;
        }

    }

    private Point getRandomPosition() {
        Point point = new Point();

        do {
            point.x = RANDOM.nextInt(grid.length);
            point.y = RANDOM.nextInt(grid.length);
        } while (grid[point.x][point.y] == State.BOMB);

        return point;
    }

}
