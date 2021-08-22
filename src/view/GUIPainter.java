package view;

import model.CellType;

import javax.swing.*;
import java.awt.*;

public class GUIPainter {

    private final int SIZE = 50;
    private JFrame frame;
    private JPanel panel;
    private Graphics g;

    public GUIPainter() {
        this.panel = new JPanel(new BorderLayout());
        this.panel.setSize(800, 560);

        this.frame = new JFrame("Snake");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(800, 600);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        frame.add(panel);

        this.g = panel.getGraphics();
    }

    //5x5
    public void paint(CellType[][] cellGrid) {
        allBlack();
        g.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cellGrid[i][j] == null) {
                } else if (cellGrid[i][j].equals(CellType.SNAKEBODY)) {
                    g.drawOval(i+100,j+100, SIZE*100, SIZE*100);
                }
            }
            System.out.println();
        }
    }

    private void allBlack() {
        g.setColor(Color.black);
        g.fillRect(0,0,1000,1000);
    }

}
