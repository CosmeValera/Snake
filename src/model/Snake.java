package model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<SnakeCell> body;
    private Direction direction;

    public Snake() {
        List<SnakeCell> snakeBodyCreated = new LinkedList<>();
        snakeBodyCreated.add(new SnakeCell(0, 0, CellType.SNAKEBODY));
        snakeBodyCreated.add(new SnakeCell(0, 1, CellType.SNAKEBODY));
        snakeBodyCreated.add(new SnakeCell(0, 2, CellType.HEAD));
        direction = Direction.RIGHT;
        this.body = snakeBodyCreated;
    }

    public List<SnakeCell> getBody() {
        return body;
    }

    public void setBody(List<SnakeCell> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
