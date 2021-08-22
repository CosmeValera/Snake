package model;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private List<SnakeBody> body;

    public Snake() {
        List<SnakeBody> snakeBodyCreated = new LinkedList<>();
        snakeBodyCreated.add(new SnakeBody(0, 1));
        snakeBodyCreated.add(new SnakeBody(0, 2));
        this.body = snakeBodyCreated;
    }

    public List<SnakeBody> getBody() {
        return body;
    }

    public void setBody(List<SnakeBody> body) {
        this.body = body;
    }
}
