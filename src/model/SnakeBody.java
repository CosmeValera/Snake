package model;

import model.CellType;

public class SnakeBody {
    private CellType type;
    private int i;
    private int j;

    public SnakeBody() {
        this.type = CellType.SNAKEBODY;
    }
    public SnakeBody(int i, int j) {
        this.type = CellType.SNAKEBODY;
        this.i = i;
        this.j = j;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
