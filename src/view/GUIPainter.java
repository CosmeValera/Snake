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

    public static Color fruitColor = Color.RED;
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
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.insertDirectionToBuffer(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.insertDirectionToBuffer(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.insertDirectionToBuffer(Direction.UP);
                }
            }
        });

        g = panel.getGraphics();
        allBlack();
    }

    public static void changeFruitColor() {
        int v = (int) (Math.random() * 3 + 1);
        if (v == 1) {
            fruitColor = Color.red;
        } else if (v == 2) {
            fruitColor = Color.green;
        } else if (v == 3) {
            fruitColor = Color.magenta;
        }
    }

    public void paint(CellType[][] cellGrid, Snake snake, int height, int width, int timeMili) {
        allBlack();
        putSnakeInCellGrid(cellGrid, snake);
        paintSnakeBodyAndFruits(cellGrid, snake, height, width, timeMili);
    }

    public void paintLoseScreen(CellType[][] cellGrid, Snake snake, int height, int width) {
//        allBlack();
        int points = calculatePoints(snake);
        JLabel label = new JLabel("<html> You have lost.<br/> Points: " + points + " </html>");
        label.setOpaque(true);
        label.setBackground(Color.black);
        label.setForeground(Color.white);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 40));
        panel.add(label); // adding a label will automatically invalidate the component
        panel.revalidate();
        panel.repaint(); // you need to repaint
//        allBlack();
    }

    private int calculatePoints(Snake snake) {
        return snake.getBody().size();
    }

    public void paintWinScreen(CellType[][] cellGrid, Snake snake, int height, int width) {
        int points = calculatePoints(snake);
        JLabel label = new JLabel("<html> You have won.<br/> Points: " + points + " </html>");
        label.setOpaque(true);
        label.setBackground(Color.black);
        label.setForeground(Color.white);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 40));
        panel.add(label); // adding a label will automatically invalidate the component
        panel.revalidate();
        panel.repaint(); // you need to repaint
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

    private void paintSnakeBodyAndFruits(CellType[][] cellGrid, Snake snake, int height, int width, int timeMili) {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cellGrid[i][j] == null || cellGrid[i][j].equals(CellType.EMPTY)) {
                    continue;
                } else if (cellGrid[i][j].equals(CellType.SNAKEBODY)) {
                    g.setColor(Color.cyan);
                } else if (cellGrid[i][j].equals(CellType.HEAD)) {
                    g.setColor(Color.blue);
                } else if (cellGrid[i][j].equals(CellType.FRUIT)) {
                    g.setColor(fruitColor);
                }
                g.fillOval((int) ((i + 0.33) * LENGTH),
                        (int) ((j + 0.33) * LENGTH),
                        (int) (0.8 * LENGTH),
                        (int) (0.8 * LENGTH));
            }
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        g.drawString("Points: " + snake.getBody().size(), LENGTH, LENGTH);
        g.drawString("Speed: " + timeMili, (int) ((double) LENGTH * rows / (rows > 15 ? 1.1 :
                (rows > 8 ? 1.2 : 1.35))) - LENGTH * 2, LENGTH * (rows > 7 ? 1 : 2));
    }
}
