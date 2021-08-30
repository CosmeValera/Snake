package model;

import view.GUIPainter;

public class GameLogic {
    private int height;
    private int width;
    private double timeInMilliseconds;
    private Snake snake;
    private CellType[][] cellGrid;

    private Boolean gameLost;

    public GameLogic(int height, int width, Snake snake, CellType[][] cellGrid) {
        this.height = height;
        this.width = width;
        this.snake = snake;
        this.cellGrid = cellGrid;
        timeInMilliseconds = 350;
        gameLost = null;
    }

    public void createFruitAtRandom() {
        int posIFruit = (int) (Math.random() * height);
        int posJFruit = (int) (Math.random() * width);
        boolean putFruit = true;

        for (SnakeCell sb : snake.getBody()) {
            if (sb.getI() == posIFruit
                    && sb.getJ() == posJFruit) {
                putFruit = false;
            }
        }
        if (cellGrid[posIFruit][posJFruit] != null && cellGrid[posIFruit][posJFruit].equals(CellType.WALL)) {
            putFruit = false;
        }

        if (gameWon()) {
            gameLost = false;
        } else if (putFruit) {
            putWallIfAppropriate();
            cellGrid[posIFruit][posJFruit] = CellType.FRUIT;
            GUIPainter.changeFruitColor();
        } else {
            createFruitAtRandom();
        }
    }

    private void putWallIfAppropriate() {
        int i = (int) (Math.random() * height);
        int j = (int) (Math.random() * width);
        if (snake.getBody().size() % 8 == 0) {
            if (cellGrid[i][j] == null || cellGrid[i][j] == CellType.EMPTY)
                cellGrid[i][j] = CellType.WALL;
            else
                putWallIfAppropriate();
        }
    }

    private boolean gameWon() {
        int walls = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cellGrid[i][j] != null && cellGrid[i][j].equals(CellType.WALL)) {
                    walls += 1;
                }
            }
        }
        boolean isGameWon = snake.getBody().size() + walls >= height * width;
        return isGameWon;
    }

    public void updateCellGrid() {
        Direction dir = snake.obtainFirstDirectionAvailableFromBuffer();
        if (dir == null)
            dir = snake.getLastDirection();
        if (collapseWithBody()) return;
        if (moveRight(dir)) return;
        if (moveLeft(dir)) return;
        if (moveUp(dir)) return;
        if (moveDown(dir)) return;
    }

    private boolean moveDown(Direction dir) {
        if (dir == Direction.DOWN) {
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() + 1);
            snake.getBody().add(sC);
            if (hitBorderOrWallThenLose()) {
                return true;
            }
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveUp(Direction dir) {
        if (dir == Direction.UP) {
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI());
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ() - 1);
            snake.getBody().add(sC);
            if (hitBorderOrWallThenLose()) {
                return true;
            }
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveLeft(Direction dir) {
        if (dir == Direction.LEFT) {
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI() - 1);
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sC);
            if (hitBorderOrWallThenLose()) {
                return true;
            }
            checkFruitOrRemoveTail();
            return true;
        }
        return false;
    }

    private boolean moveRight(Direction dir) {
        if (dir == Direction.RIGHT) {
            oldHeadTurnsToBody();
            SnakeCell sC = createNewHead();
            sC.setI(snake.getBody().get(snake.getBody().size() - 1).getI() + 1);
            sC.setJ(snake.getBody().get(snake.getBody().size() - 1).getJ());
            snake.getBody().add(sC);
            if (hitBorderOrWallThenLose()) {
                return true;
            }
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

    private boolean hitBorderOrWallThenLose() {
        if (hitsBorder() || hitsAWall()) {
            gameLost = true;
            return true;
        }
        return false;
    }

    private boolean hitsBorder() {
        if (snake.getBody().get(snake.getBody().size() - 1).getJ() >= width ||
                snake.getBody().get(snake.getBody().size() - 1).getJ() < 0 ||
                snake.getBody().get(snake.getBody().size() - 1).getI() < 0 ||
                snake.getBody().get(snake.getBody().size() - 1).getI() >= height) {
            return true;
        }
        return false;
    }

    private boolean hitsAWall() {
        return cellGrid[snake.getBody().get(snake.getBody().size() - 1).getI()][snake.getBody().get(snake.getBody().size() - 1).getJ()] == CellType.WALL;
    }

    private boolean collapseWithBody() {
        for (int i = 0; i < snake.getBody().size() - 1; i++) {
            SnakeCell bodyPiece = snake.getBody().get(i);
            if (bodyPiece.getI() == snake.getBody().get(snake.getBody().size() - 1).getI()
                    && bodyPiece.getJ() == snake.getBody().get(snake.getBody().size() - 1).getJ()) {
                gameLost = true;
                return true;
            }
        }
        return false;
    }

    public Boolean isGameLost() {
        return gameLost;
    }

    public void setGameLost(boolean gameLost) {
        this.gameLost = gameLost;
    }

    public double obtainTimeInMilliseconds() {
        timeInMilliseconds = 400 - (((350 / (double) (height * width)) * snake.getBody().size()));
        return timeInMilliseconds;
    }

}
