package view;

import model.CellType;

public class PaintConsole {
    public void paint(CellType[][] cellGrid, int height, int width) {

        System.out.println("---------------");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cellGrid[i][j]==null|| cellGrid[i][j].equals(CellType.EMPTY) ){
                    System.out.print(" _ ");
                }
                else if (cellGrid[i][j].equals(CellType.SNAKEBODY)){
                    System.out.print(" O ");
                }
                else if (cellGrid[i][j].equals(CellType.HEAD)){
                    System.out.print(" C ");
                }
                else if (cellGrid[i][j].equals(CellType.FRUIT)){
                    System.out.print(" F ");
                }
           }
            System.out.println();
        }
    }
}
