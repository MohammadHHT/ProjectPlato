package model.BattleSea;

public class Ship {
    private char shipName;
    private int row;
    private int column;
    private int length;
    private int width;
    private int direction;
    public static final int Unset = -1;
    public static final int Horizontal = 0;
    public static final int Vertical = 1;

    public Ship(char shipName, int length, int width) {
        this.shipName = shipName;
        this.length = length;
        this.width = width;
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

    public void setLocation(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setDirection(int direction) {
        if (direction != Unset && direction != Horizontal && direction != Vertical)
            throw new IllegalArgumentException("Invalid direction.\n" +
                    "It must be -1(Unset), 0(Horizontal), or 1(Vertical)");
        this.direction = direction;
    }

    public char getShipName() {
        return shipName;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getDirection() {
        return direction;
    }

    private String directionToString() {
        if (direction == Unset)
            return "Unset";
        else if (direction == Horizontal)
            return "Horizontal";
        else
            return "Vertical";
    }
    public String toString() {
        return "Ship: " + getShipName() + "\n" +
                "(" + getRow() + ", " + getColumn() + ") \n" +
                " length: " + getLength() + "\n" +
                " width: " + getWidth() + "\n" +
                " and direction: " + directionToString();
    }
}
