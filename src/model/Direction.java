package model;

public enum Direction {
    UP, DOWN, RIGHT, LEFT;

    public static Direction oppositeDirection(Direction dir) {
        if (dir == UP) {
            return DOWN;
        }
        if (dir == DOWN) {
            return UP;
        }
        if (dir == LEFT) {
            return RIGHT;
        }
        if (dir == RIGHT) {
            return LEFT;
        }
        return null;
    }
}
