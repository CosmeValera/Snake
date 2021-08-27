package model;

import view.GUIPainter;

import java.util.Scanner;

public class SnakeGame {

    public static Scanner in = new Scanner(System.in);

    public int rows;
    public int columns;
    Snake snake;
    CellType[][] cellGrid;

    public SnakeGame(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public void play() throws InterruptedException {
        snake = new Snake();
        cellGrid = new CellType[rows][columns];
        GameLogic gL = new GameLogic(rows, columns, snake, cellGrid);
        gL.createFruitAtRandom();

        GUIPainter gP = new GUIPainter(rows, columns, snake);
        while (true) {
            //GUI
            System.out.println(snake.getDirectionsBuffer()[0] + ", " + snake.getDirectionsBuffer()[1] + ", " + snake.getDirectionsBuffer()[2]);
            if (gL.isGameLost()) {
                break;
            }
            gL.updateCellGrid();
            gP.paint(cellGrid, snake, rows, columns);
            Thread.sleep(300);
        }
        gP.paintLoseScreen(cellGrid, snake, rows, columns);
    }
}