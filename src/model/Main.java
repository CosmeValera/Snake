package model;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SnakeGame sg = new SnakeGame(8, 8);
        sg.play();
    }
}
