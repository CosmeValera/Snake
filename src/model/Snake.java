package model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<SnakeCell> body;
    private Direction lastDirection;
    private Direction[] directionsBuffer = new Direction[3];

    public Snake() {
        List<SnakeCell> snakeBodyCreated = new LinkedList<>();
        snakeBodyCreated.add(new SnakeCell(0, 0, CellType.SNAKEBODY));
        snakeBodyCreated.add(new SnakeCell(0, 1, CellType.SNAKEBODY));
        snakeBodyCreated.add(new SnakeCell(0, 2, CellType.HEAD));
        snakeBodyCreated.add(new SnakeCell(0, 3, CellType.HEAD));

        insertDirectionToBuffer(Direction.RIGHT);

        this.body = snakeBodyCreated;
    }

    public void insertDirectionToBuffer(Direction dir) {
        if (directionsBuffer[0] == null) {
            directionsBuffer[0] = dir;
        } else if (directionsBuffer[1] == null) {
            directionsBuffer[1] = dir;

        } else if (directionsBuffer[2] == null) {
            directionsBuffer[2] = dir;
        }
    }

    public Direction obtainFirstDirectionAvailableFromBuffer() {
        Direction dir = null;
        if (directionsBuffer[2] != null) {
            dir = directionsBuffer[2];
            lastDirection = directionsBuffer[2];
            directionsBuffer[2] = null;
        } else if (directionsBuffer[1] != null) {
            dir = directionsBuffer[1];
            lastDirection = directionsBuffer[1];
            directionsBuffer[1] = null;
        } else if (directionsBuffer[0] != null) {
            dir = directionsBuffer[0];
            lastDirection = directionsBuffer[0];
            directionsBuffer[0] = null;
        }
        return dir;
    }

    public List<SnakeCell> getBody() {
        return body;
    }

    public void setBody(List<SnakeCell> body) {
        this.body = body;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public Direction[] getDirectionsBuffer() {
        return directionsBuffer;
    }

}
