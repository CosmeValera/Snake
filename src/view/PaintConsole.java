package view;

import model.CellType;
import model.Snake;
import model.SnakeBody;

public class PaintConsole { //5x5
    public void paint(CellType[][] cellGrid, int HEIGHT, int WIDTH) {

        //PAINT
        System.out.println("---------------");
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (cellGrid[i][j]==null|| cellGrid[i][j].equals(CellType.EMPTY) ){
                    System.out.print(" _ ");
                }
                else if (cellGrid[i][j].equals(CellType.SNAKEBODY)){
                    System.out.print(" O ");
                }
                else if (cellGrid[i][j].equals(CellType.FRUIT)){
                    System.out.print(" F ");
                }
           }
            System.out.println();
        }
    }
}
