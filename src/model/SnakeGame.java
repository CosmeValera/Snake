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
            //LOGIC
//            for (SnakeCell pieceOfBody : snake.getBody()) {
//                cellGrid[pieceOfBody.getI()][pieceOfBody.getJ()] = pieceOfBody.getType();
//            }

//            //CONSOLE
//            if (gL.updatedCellGrid(cellGrid, height, width)) break;

            //GUI
            System.out.println(snake.getDirectionsBuffer()[0] + ", " + snake.getDirectionsBuffer()[1] + ", " + snake.getDirectionsBuffer()[2]);
            gL.updateCellGrid(cellGrid, rows, columns);
            gP.paint(cellGrid, snake, rows, columns);
            Thread.sleep(2000);
        }
//        System.out.println("You've lost!");
    }
}