package main.back.game.SeaBattle;

public class Ship {

    private int[] cells;
    private int destroyed;

    public Ship(int... cells) {
        this.cells = new int[cells.length];
        System.arraycopy(cells, 0, this.cells, 0, cells.length);
        destroyed = 0;
    }

    public int getDestroyed() {
        return destroyed;
    }

    public boolean isThere(int num) {
        for (int c : cells) {
            if (c == num) {
                destroyed++;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        switch (cells.length) {
            case 8:
                return "4x2";
            case 6:
                return "3x2";
            case 4:
                return "4x1";
            case 2:
                return "2x1";
        }
        return null;
    }
}
