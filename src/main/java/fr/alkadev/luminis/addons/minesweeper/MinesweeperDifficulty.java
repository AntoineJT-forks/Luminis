package fr.alkadev.luminis.addons.minesweeper;

public enum MinesweeperDifficulty {

    EASY(25),
    MEDIUM(35),
    HARD(55),
    EXPERT(75);

    private final int bombAmount;

    MinesweeperDifficulty(int bombAmount){
        this.bombAmount = bombAmount;
    }

    public int getBombAmount() {
        return bombAmount;
    }

}
