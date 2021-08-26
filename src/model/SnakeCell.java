package model;

public class SnakeCell {
    private CellType type;
    private int i;
    private int j;

    public SnakeCell() {
        this.type = CellType.SNAKEBODY;
    }
    public SnakeCell(int i, int j, CellType type) {
        this.type = type;
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
