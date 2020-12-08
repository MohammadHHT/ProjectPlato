package model.BattleSea;

public class Ship {
    private int row;
    private int column;
    private int length;
    private int direction;
    public static final int Unset = -1;
    public static final int Horizontal = 0;
    public static final int Vertical = 1;
    
    public Ship(int width, int length, boolean isHorizontal) {
        this.width = width;
        this.length = length;
        this.isHorizontal = isHorizontal;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setHorizontal(boolean horizontal) {
        isHorizontal = horizontal;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public int getLength() {
        return length;
    }

    public boolean isWreck(BattleSeaPlayer player) {
        return false;
    }

    public boolean canChangeDirection() {
        return false;
    }

    public void changeDirection() {
        //TODO
    }

    public boolean canMove(int x, int y) {
        return false;
    }

    public void move(int x, int y) {
        //TODO
    }
}
