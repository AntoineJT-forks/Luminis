package fr.alkadev.luminis.addons.minesweeper.model;

import fr.alkadev.luminis.addons.minesweeper.State;

import java.awt.Point;

class NumberPlacer {

    private final State[][] grid;

    NumberPlacer(State[][] grid) {
        this.grid = grid;
    }

    void placeNumbers() {
        for (int x = 0; x < grid.length; x++) {
            placeLineNumbers(x);
        }
    }

    private void placeLineNumbers(int x) {
        for (int y = 0; y < grid.length; y++) {
            int bombCount = countBombs(new Point(x, y));
            grid[x][y] = State.fromBombCount(bombCount);
        }
    }

    private int countBombs(Point point) {
        State current = grid[point.x][point.y];

        if (current == State.BOMB) {
            return State.BOMB.ordinal();
        }

        int counter = 0;

        for (int x = point.x - 1; x <= point.x + 1; x++) {
            counter = countBombWithX(counter, x, point);
        }

        return counter;
    }

    private int countBombWithX(int counter, int x, Point point) {

        for (int y = point.y - 1; y <= point.y + 1; y++) {
            counter = getCounter(counter, new Point(x, y));
        }

        return counter;
    }

    private int getCounter(int counter, Point point) {

        if (!(0 <= point.x && point.x < grid.length) || !(0 <= point.y && point.y < grid.length)) {
            return counter;
        }

        if (grid[point.x][point.y] == State.BOMB) {
            ++counter;
        }

        return counter;
    }

}
