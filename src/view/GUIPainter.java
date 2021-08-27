package view;

import model.CellType;
import model.Direction;
import model.Snake;
import model.SnakeCell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUIPainter {

    public static final int LENGTH = 30;

    private int rows;
    private int columns;

    private JFrame frame;
    private JPanel panel;
    private Graphics g;

    public GUIPainter(int rows, int columns, Snake snake) {
        this.rows = rows;
        this.columns = columns;

        panel = new JPanel(new BorderLayout());
        panel.setSize(rows * LENGTH + 15, columns * LENGTH + 45);
        panel.setVisible(true);
        panel.requestFocusInWindow();

        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(rows * LENGTH + 15, columns * LENGTH + 45);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(panel);

        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.insertDirectionToBuffer(Direction.RIGHT);
                    System.out.println("Key pressed right");
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.insertDirectionToBuffer(Direction.LEFT);
                    System.out.println("Key LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.insertDirectionToBuffer(Direction.DOWN);
                    System.out.println("Key pressed down");
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.insertDirectionToBuffer(Direction.UP);
                    System.out.println("Key pressed up");
                }
            }
        });

        g = panel.getGraphics();
        allBlack();
    }

    public void paint(CellType[][] cellGrid, Snake snake, int height, int width) {
        allBlack();
        putSnakeInCellGrid(cellGrid, snake);
        paintSnakeBodyAndFruits(cellGrid, height, width);
    }

    private void allBlack() {
        g.setColor(Color.black);
        g.fillRect(0, 0, rows * LENGTH + 15, columns * LENGTH + 45);
    }

    private void putSnakeInCellGrid(CellType[][] cellGrid, Snake snake) {
        for (SnakeCell pieceOfBody : snake.getBody()) {
            cellGrid[pieceOfBody.getI()][pieceOfBody.getJ()] = pieceOfBody.getType();
        }
    }

    private void paintSnakeBodyAndFruits(CellType[][] cellGrid, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cellGrid[i][j] == null || cellGrid[i][j].equals(CellType.EMPTY)) {
                    continue;
                } else if (cellGrid[i][j].equals(CellType.SNAKEBODY)) {
                    g.setColor(Color.cyan);
                } else if (cellGrid[i][j].equals(CellType.HEAD)) {
                    g.setColor(Color.blue);
                } else if (cellGrid[i][j].equals(CellType.FRUIT)) {
                    g.setColor(Color.red);
                }
                g.fillOval((int) ((i + 0.33) * LENGTH),
                        (int) ((j + 0.33) * LENGTH),
                        (int) (0.8 * LENGTH),
                        (int) (0.8 * LENGTH));
            }
        }
    }

}
