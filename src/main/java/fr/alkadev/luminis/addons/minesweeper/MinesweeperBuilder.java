package fr.alkadev.luminis.addons.minesweeper;

import java.util.Random;

public class MinesweeperBuilder {

    private MinesweeperDifficulty difficulty;

    public MinesweeperBuilder withDifficulty(final MinesweeperDifficulty difficulty){
        this.difficulty = difficulty;
        return this;
    }

    private static final int GRID_SIZE = 13;
    final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

    public String getMinesweeperMessage(){
        fillGrid();
        final StringBuilder sb = new StringBuilder("Démineur : "+ difficulty.getBombAmount() +" bombes :bomb:\n\n");
        boolean isStart = false;
        for(int[] columns : grid){
            for(int cell : columns){
                if(cell == 0 && !isStart){
                    sb.append(getSymbol(cell)).append(" ");
                    isStart = true;
                }else{
                    sb.append("||").append(getSymbol(cell)).append("|| ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void fillGrid(){
        // Generate bombs (-1 -> bomb)
        final Random random = new Random();
        for (int i = 0; i < difficulty.getBombAmount(); i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            while(grid[x][y] == -1){
                x = random.nextInt(10);
                y = random.nextInt(10);
            }

            grid[x][y] = -1;
        }
        // Count bombs next to each cell
        for(int x = 0; x<grid.length; x++){
            for(int y = 0; y<grid.length; y++){
                grid[x][y] = countBombs(x, y);
            }
        }
    }

    private int countBombs(final int x, final int y){
        final int current = grid[x][y];
        if(current == -1) return -1;
        int counter = 0;
        for(int oX = -1; oX <= 1; oX++){
            for(int oY = -1; oY <= 1; oY++){
                if(oX+x < 0 || oX+x > grid.length-1 || oY + y < 0 || oY + y > grid.length-1) continue;
                if(grid[oX+x][oY+y] == -1) counter++;
            }
        }
        return counter;
    }

    private String getSymbol(final int cell){
        switch (cell){
            case -1:
                return ":bomb:";
            case 0:
                return ":zero:";
            case 1:
                return ":one:";
            case 2:
                return ":two:";
            case 3:
                return ":three:";
            case 4:
                return ":four:";
            case 5:
                return ":five:";
            case 6:
                return ":six:";
            case 7:
                return ":seven:";
            case 8:
                return ":eight:";
            default:
                return "";
        }
    }

}
