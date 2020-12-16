package model.BattleSea;

public class Grid {
    private Location[][] grid;
    private int points;

    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;

    public Grid() {
        if (NUM_ROWS > 26) {
            throw new IllegalArgumentException("ERROR! NUM_ROWS CANNOT BE > 26");
        }

        grid = new Location[NUM_ROWS][NUM_COLS];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Location tempLoc = new Location();
                grid[row][col] = tempLoc;
            }
        }
    }

    public void markHit(int row, int col) {
        grid[row][col].markHit();
        points++;
    }

    public void markMiss(int row, int col) {
        grid[row][col].markMiss();
    }

    public void setStatus(int row, int col, int status) {
        grid[row][col].setStatus(status);
    }

    public int getStatus(int row, int col) {
        return grid[row][col].getStatus();
    }

    public boolean alreadyGuessed(int row, int col) {
        return !grid[row][col].isUnGuessed();
    }

    public void setShip(int row, int col, boolean val) {
        grid[row][col].setShip(val);
    }

    public boolean hasShip(int row, int col) {
        return grid[row][col].hasShip();
    }

    public Location get(int row, int col) {
        return grid[row][col];
    }

    public int numRows() {
        return NUM_ROWS;
    }

    public int numCols() {
        return NUM_COLS;
    }

    public void printPlayerGrid() {
        generalPrintMethod(0);
    }

    public void printShips() {
        generalPrintMethod(1);
    }

    public void printOppGrid() {
        generalPrintMethod(2);
    }

    public boolean hasWin() {
        return points == 25;
    }

    public void addShip(Ship s) {
        int row = s.getRow();
        int col = s.getColumn();
        int length = s.getLength();
        int width = s.getWidth();
        String shipName = s.getShipName();
        int dir = s.getDirection();

        if (!(s.isDirectionSet()) || !(s.isLocationSet()))
            throw new IllegalArgumentException("ERROR! Direction or Location is unset/default");

        // 0 - hor; 1 - ver
        // Horizontal
        if (dir == 0) {
            for (int i = row; i < row + width; i++) {
                for (int j = col; j < col + length; j++) {
                    grid[i][j].setShip(true);
                    grid[i][j].setLengthOfShip(length);
                    grid[i][j].setShipName(shipName);
                    grid[i][j].setDirectionOfShip(dir);
                }
            }
            // Vertical
        } else if (dir == 1) {
            for (int i = col; i < col + row; i++) {
                for (int j = row; j < row + length; j++) {
                    grid[j][i].setShip(true);
                    grid[j][i].setLengthOfShip(length);
                    grid[i][j].setShipName(shipName);
                    grid[j][i].setDirectionOfShip(dir);
                }
            }
        }
    }

    private void generalPrintMethod(int type) {
        System.out.println();

        System.out.print("   ");
        for (int i = 0; i < NUM_COLS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.println(i);
            for (int j = 0; j < NUM_COLS; j++) {
                if (type == 0) {
                    if (grid[i][j].isUnGuessed())
                        System.out.print("O ");
                    else if (grid[i][j].checkMiss())
                        System.out.print("- ");
                    else if (grid[i][j].checkHit())
                        System.out.print("+ ");
                    else if (grid[i][j].isAllHit())
                        System.out.println("* ");
                    else if (grid[i][j].hasShip())
                        System.out.println("# ");
                } else if (type == 1) {
                    if (grid[i][j].hasShip()) {
                        if (grid[i][j].getShipName() == "A") {
                            System.out.print("A ");
                        } else if (grid[i][j].getShipName() == "B") {
                            System.out.print("B ");
                        } else if (grid[i][j].getShipName() == "C") {
                            System.out.print("C ");
                        } else if (grid[i][j].getShipName() == "D") {
                            System.out.print("D ");
                        } else if (grid[i][j].getShipName() == "E") {
                            System.out.println("E ");
                        } else if (grid[i][j].getShipName() == "F") {
                            System.out.println("F ");
                        }
                    } else if (!(grid[i][j].hasShip())) {
                        System.out.print("O ");
                    }
                } else if (type == 2) {
                    if (grid[i][j].isUnGuessed())
                        System.out.print("O ");
                    else if (grid[i][j].checkMiss())
                        System.out.print("- ");
                    else if (grid[i][j].checkHit())
                        System.out.print("+ ");
                    else if (grid[i][j].isAllHit())
                        System.out.println("* ");
                }
            }
        }
            System.out.println();
    }
}
