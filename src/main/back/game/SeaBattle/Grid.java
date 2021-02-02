package main.back.game.SeaBattle;

import java.util.Arrays;
import java.util.Random;

public class Grid {

    private Status[] grid;
    private Ship[] ships;
    private int destroyed;

    public Grid() {
        grid = new Status[100];
        Arrays.fill(grid, Status.NORMAL);
        ships = new Ship[5];
        destroyed = 0;
    }

    public int getDestroyed() {
        return destroyed;
    }

    public void setShips(int[][] ships) {
        for (int i = 0; i < 5; i++) {
            this.ships[i] = new Ship(ships[i]);
        }
    }

    public String mark(int num) {               // returns ship name if hit (e.g: 4x2) otherwise returns null (survived)
        Ship ship = isShip(num);
        if (ship == null) {
            mark(num, Status.SURVIVED);
            return null;
        } else {
            destroyed++;
            mark(num, Status.HIT);
            return ship.toString();
        }
    }

    private void mark(int num, Status status) {
        grid[num - 1] = status;
    }

    private Ship isShip(int num) {
        for (Ship s : ships) {
            if (s.isThere(num)) {
                return s;
            }
        }
        return null;
    }

    public int[][] random() {
        boolean[] tmp = new boolean[100];
        Arrays.fill(tmp, false);

        int[][] ships = new int[5][];
        ships[0] = random8(tmp);
        ships[1] = random6(tmp);
        ships[2] = random4(tmp);
        ships[3] = random2(tmp);
        ships[4] = random2(tmp);
        return ships;
    }

    private int[] random8(boolean[] tmp) {
        Random random = new Random();
        int[] pos = new int[8];
        if (random.nextBoolean()) {     // horizontal
            do {
                pos[0] = 1;
                pos[1] = 2;
                pos[2] = 3;
                pos[3] = 4;
                pos[4] = 11;
                pos[5] = 12;
                pos[6] = 13;
                pos[7] = 14;
                int xOffset = random.nextInt(7);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        } else {                        // vertical
            do {
                pos[0] = 1;
                pos[1] = 2;
                pos[2] = 11;
                pos[3] = 12;
                pos[4] = 21;
                pos[5] = 22;
                pos[6] = 31;
                pos[7] = 32;
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(7);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        }
    }

    private int[] random6(boolean[] tmp) {
        Random random = new Random();
        int[] pos = new int[6];
        if (random.nextBoolean()) {     // horizontal
            do {
                pos[0] = 1;
                pos[1] = 2;
                pos[2] = 3;
                pos[3] = 11;
                pos[4] = 12;
                pos[5] = 13;
                int xOffset = random.nextInt(8);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        } else {                        //vertical
            do {
                pos[0] = 1;
                pos[1] = 2;
                pos[2] = 11;
                pos[3] = 12;
                pos[4] = 21;
                pos[5] = 22;
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(8);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        }
    }

    private int[] random4(boolean[] tmp) {
        Random random = new Random();
        int[] pos = new int[4];
        if (random.nextBoolean()) {     // horizontal
            do {
                pos[0] = 1;
                pos[1] = 2;
                pos[2] = 3;
                pos[3] = 4;
                int xOffset = random.nextInt(7);
                int yOffset = random.nextInt(10);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        } else {                        //vertical
            do {
                pos[0] = 1;
                pos[1] = 11;
                pos[2] = 21;
                pos[3] = 31;
                int xOffset = random.nextInt(10);
                int yOffset = random.nextInt(7);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        }
    }

    private int[] random2(boolean[] tmp) {
        Random random = new Random();
        int[] pos = new int[2];
        if (random.nextBoolean()) {     // horizontal
            do {
                pos[0] = 1;
                pos[1] = 2;
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(10);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        } else {                        //vertical
            do {
                pos[0] = 1;
                pos[1] = 11;
                int xOffset = random.nextInt(10);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos.length; i++) {
                    pos[i] += pos[i] * yOffset * 10 + xOffset;
                }
            } while (!available(tmp, pos));
            for (int p : pos) {
                tmp[p] = true;
            }
            return pos;
        }
    }

    private boolean available(boolean[] tmp, int... cells) {
        for (int c : cells) {
            if (!(c > 0 && c < 101 && !tmp[c - 1])) return false;
        }
        return true;
    }
}

enum Status {
    NORMAL, HIT, SURVIVED
}