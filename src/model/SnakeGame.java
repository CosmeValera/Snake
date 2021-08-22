package model;

import view.PaintConsole;

import java.util.Scanner;

public class SnakeGame {

    public static Scanner in = new Scanner(System.in);
    public final int HEIGHT = 4;
    public final int WIDTH = 5;
    Snake snake;
    CellType[][] cellGrid;

    public void play() throws InterruptedException {
        snake = new Snake();
        cellGrid = new CellType[HEIGHT][WIDTH];
        createFruitAtRandom();
        while (true) {
            //LOGIC
            for (SnakeBody pieceOfBody : snake.getBody()) {
                System.out.println("loop");
                cellGrid[pieceOfBody.getI()][pieceOfBody.getJ()] = pieceOfBody.getType();
            }

            //CONSOLE
            if (consoleLogic(cellGrid, HEIGHT, WIDTH)) break;

            //GUI
//            GUIPainter gP = new GUIPainter();
//            gP.paint(cellGrid);
//            Thread.sleep(1000);
        }
        System.out.println("You've lost!");
    }

    private void createFruitAtRandom() throws StackOverflowError{
        int i = (int)(Math.random()*HEIGHT);
        int j = (int)(Math.random()*WIDTH);
        boolean putFruit = true;

        for (SnakeBody sb : snake.getBody()) {
            if (sb.getI() == i
                && sb.getJ() == j) {
                putFruit= false;
            }
        }
        if (putFruit) {
            cellGrid[i][j] = CellType.FRUIT;
        } else {
            createFruitAtRandom();
        }
    }

    private boolean consoleLogic(CellType[][] cellGrid, int HEIGHT, int WIDTH) {
        PaintConsole pc = new PaintConsole();
        pc.paint(cellGrid, HEIGHT, WIDTH);

        System.out.print("wasd: ");
        char dir = in.next().toLowerCase().charAt(0);

        //MovementConsole
        if (moveRight(dir)) return true;
        if (moveLeft(dir)) return true;
        if (moveUp(dir)) return true;
        if (moveDown(dir)) return true;
        if (collapseWithBody()) {
            return true;
        }
        return false;
    }

    private boolean moveRight(char dir) {
        if (dir == 'd') {//MOve right
            if (snake.getBody().get(snake.getBody().size() - 1).getJ() + 1 >= WIDTH) {
                return true;
            }
            SnakeBody sB = new SnakeBody();
            sB.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sB.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() + 1);
            snake.getBody().add(sB);
            checkFruitOrRemoveTail();
        }
        return false;
    }

    private boolean moveLeft(char dir) {
        if (dir == 'a') {
            if (snake.getBody().get(snake.getBody().size() - 1).getJ() - 1 < 0) {
                return true;
            }
            SnakeBody sB = new SnakeBody();
            sB.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sB.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() - 1);
            snake.getBody().add(sB);
            checkFruitOrRemoveTail();
        }
        return false;
    }

    private boolean moveUp(char dir) {
        if (dir == 'w') {
            if (snake.getBody().get(snake.getBody().size() - 1).getI() - 1 < 0) {
                return true;
            }
            SnakeBody sB = new SnakeBody();
            sB.setI(snake.getBody().get(snake.getBody().size() - 1).getI() - 1);
            sB.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sB);
            checkFruitOrRemoveTail();
        }
        return false;
    }

    private boolean moveDown(char dir) {
        if (dir == 's') {
            if (snake.getBody().get(snake.getBody().size() - 1).getI() + 1 >= HEIGHT) {
                return true;
            }
            SnakeBody sB = new SnakeBody();
            sB.setI(snake.getBody().get(snake.getBody().size() - 1).getI() + 1);
            sB.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sB);
            checkFruitOrRemoveTail();
        }
        return false;
    }

    private void checkFruitOrRemoveTail() {
        cellGrid[snake.getBody().get(0).getI()][snake.getBody().get(0).getJ()] = CellType.EMPTY;
        if (cellGrid[snake.getBody().get(snake.getBody().size() - 1).getI()][snake.getBody().get(snake.getBody().size() - 1).getJ()]!=null
                && cellGrid[snake.getBody().get(snake.getBody().size() - 1).getI()][snake.getBody().get(snake.getBody().size() - 1).getJ()].equals(CellType.FRUIT)) {
            createFruitAtRandom();
        } else {
            snake.getBody().remove(0);
        }
    }

    private boolean collapseWithBody() {
        for (int i = 0; i < snake.getBody().size()-1; i++) {
            SnakeBody bodyPiece = snake.getBody().get(i);
            if (bodyPiece.getI() == snake.getBody().get(snake.getBody().size() - 1).getI()
                    && bodyPiece.getJ() == snake.getBody().get(snake.getBody().size() - 1).getJ()) {
                return true;
            }
        }
        return false;
    }
}
