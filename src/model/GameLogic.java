package model;

import java.util.Scanner;

public class GameLogic {
    public static Scanner in = new Scanner(System.in);
    private int height;
    private int width;
    private Snake snake;
    private CellType[][] cellGrid;

    public GameLogic(int height, int width, Snake snake, CellType[][] cellGrid) {
        this.height = height;
        this.width = width;
        this.snake = snake;
        this.cellGrid = cellGrid;
    }

    public void createFruitAtRandom() {
        int i = (int) (Math.random() * height);
        int j = (int) (Math.random() * width);
        boolean putFruit = true;

        for (SnakeCell sb : snake.getBody()) {
            if (sb.getI() == i
                    && sb.getJ() == j) {
                putFruit = false;
            }
        }
        if (putFruit) {
            cellGrid[i][j] = CellType.FRUIT;
        } else {
            createFruitAtRandom();
        }
    }

    public void updateCellGrid(CellType[][] cellGrid, int HEIGHT, int WIDTH) {
        movement();
    }

//    private boolean consolePaintAndMove(CellType[][] cellGrid, int HEIGHT, int WIDTH) {
////        PaintConsole pc = new PaintConsole();
////        pc.paint(cellGrid, HEIGHT, WIDTH);
//
//        if (movement()) return true;
//        return false;
//    }

    private void movement() {
        Direction dir = snake.obtainFirstDirectionAvailableFromBuffer();
        if (dir == null)
            dir = snake.getLastDirection();
        if (collapseWithBodyOrWall()) return;
        if (moveRight(dir)) return;
        if (moveLeft(dir)) return;
        if (moveUp(dir)) return;
        if (moveDown(dir)) return;
    }

    private boolean moveDown(Direction dir) {
        if (dir == Direction.DOWN) {
            if (snake.getBody().get(snake.getBody().size() - 1).getJ() + 1 >= width) {
                return true;
            }
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() + 1);
            snake.getBody().add(sC);
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveUp(Direction dir) {
        if (dir == Direction.UP) {
            if (snake.getBody().get(snake.getBody().size() - 1).getJ() - 1 < 0) {
                return true;
            }
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() - 1);
            snake.getBody().add(sC);
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveLeft(Direction dir) {
        if (dir == Direction.LEFT) {
            if (snake.getBody().get(snake.getBody().size() - 1).getI() - 1 < 0) {
                return true;
            }
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI() - 1);
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sC);
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveRight(Direction dir) {
        if (dir == Direction.RIGHT) {
            if (snake.getBody().get(snake.getBody().size() - 1).getI() + 1 >= height) {
                return true;
            }
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI() + 1);
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sC);
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private SnakeCell createNewHead() {
        SnakeCell sB = new SnakeCell();
        sB.setType(CellType.HEAD);
        return sB;
    }

    private void oldHeadTurnsToBody() {
        snake.getBody().get(snake.getBody().size() - 1).setType(CellType.SNAKEBODY);
    }

    private void checkFruitOrRemoveTail() {
        cellGrid[snake.getBody().get(0).getI()][snake.getBody().get(0).getJ()] = CellType.EMPTY;
        if (cellGrid[snake.getBody().get(snake.getBody().size() - 1).getI()][snake.getBody().get(snake.getBody().size() - 1).getJ()] != null
                && cellGrid[snake.getBody().get(snake.getBody().size() - 1).getI()][snake.getBody().get(snake.getBody().size() - 1).getJ()].equals(CellType.FRUIT)) {
            createFruitAtRandom();
        } else {
            snake.getBody().remove(0);
        }
    }

    private boolean collapseWithBodyOrWall() {
        for (int i = 0; i < snake.getBody().size() - 1; i++) {
            SnakeCell bodyPiece = snake.getBody().get(i);
            if (bodyPiece.getI() == snake.getBody().get(snake.getBody().size() - 1).getI()
                    && bodyPiece.getJ() == snake.getBody().get(snake.getBody().size() - 1).getJ()) {
                return true;
            }
            if (bodyPiece.getI()<0 ||bodyPiece.getI()>height
                || bodyPiece.getJ()<0 ||bodyPiece.getJ()>width) {
                return true;
            }
        }

        return false;
    }
}
