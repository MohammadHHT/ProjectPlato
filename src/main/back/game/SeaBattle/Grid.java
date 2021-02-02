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
        int[] pos1 = {1, 2, 3, 4, 11, 12, 13, 14};      // horizontal
        int[] pos2 = {1, 2, 11, 12, 21, 22, 31, 32};    //vertical
        if (random.nextBoolean()) {
            do {
                int xOffset = random.nextInt(7);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos1.length; i++) {
                    pos1[i] += pos1[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos1));
            for (int p : pos1) {
                tmp[p] = true;
            }
            return pos1;
        } else {
            do {
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(7);
                for (int i = 0; i < pos2.length; i++) {
                    pos2[i] += pos2[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos2));
            for (int p : pos2) {
                tmp[p] = true;
            }
            return pos2;
        }
    }

    private int[] random6(boolean[] tmp) {
        Random random = new Random();
        int[] pos1 = {1, 2, 3, 11, 12, 13};      // horizontal
        int[] pos2 = {1, 2, 11, 12, 21, 22};     //vertical
        if (random.nextBoolean()) {
            do {
                int xOffset = random.nextInt(8);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos1.length; i++) {
                    pos1[i] += pos1[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos1));
            for (int p : pos1) {
                tmp[p] = true;
            }
            return pos1;
        } else {
            do {
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(8);
                for (int i = 0; i < pos2.length; i++) {
                    pos2[i] += pos2[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos2));
            for (int p : pos2) {
                tmp[p] = true;
            }
            return pos2;
        }
    }

    private int[] random4(boolean[] tmp) {
        Random random = new Random();
        int[] pos1 = {1, 2, 3, 4};      // horizontal
        int[] pos2 = {1, 11, 21, 31};   //vertical
        if (random.nextBoolean()) {
            do {
                int xOffset = random.nextInt(7);
                int yOffset = random.nextInt(10);
                for (int i = 0; i < pos1.length; i++) {
                    pos1[i] += pos1[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos1));
            for (int p : pos1) {
                tmp[p] = true;
            }
            return pos1;
        } else {
            do {
                int xOffset = random.nextInt(10);
                int yOffset = random.nextInt(7);
                for (int i = 0; i < pos2.length; i++) {
                    pos2[i] += pos2[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos2));
            for (int p : pos2) {
                tmp[p] = true;
            }
            return pos2;
        }
    }

    private int[] random2(boolean[] tmp) {
        Random random = new Random();
        int[] pos1 = {1, 2};      // horizontal
        int[] pos2 = {1, 11};     //vertical
        if (random.nextBoolean()) {
            do {
                int xOffset = random.nextInt(9);
                int yOffset = random.nextInt(10);
                for (int i = 0; i < pos1.length; i++) {
                    pos1[i] += pos1[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos1));
            for (int p : pos1) {
                tmp[p] = true;
            }
            return pos1;
        } else {
            do {
                int xOffset = random.nextInt(10);
                int yOffset = random.nextInt(9);
                for (int i = 0; i < pos2.length; i++) {
                    pos2[i] += pos2[i] * yOffset * 10 + xOffset;
                }
            } while (available(tmp, pos2));
            for (int p : pos2) {
                tmp[p] = true;
            }
            return pos2;
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