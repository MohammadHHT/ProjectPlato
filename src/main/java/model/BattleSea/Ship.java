package model.BattleSea;

public class Ship {
    private int row;
    private int column;
    private int length;
    private int direction;
    public static final int Unset = -1;
    public static final int Horizontal = 0;
    public static final int Vertical = 1;

    public Ship(int length) {
        this.length = length;
        this.row = -1;
        this.column = -1;
        this.direction = Unset;
    }

    public boolean isLocationSet() {
        return row != -1 && column != -1;
    }

    public boolean isDirectionSet() {
        return direction == Unset;
    }
}
