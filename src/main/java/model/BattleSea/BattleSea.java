package model.BattleSea;

import java.util.Scanner;

public class BattleSea {

    public static Scanner scanner = new Scanner(System.in);
    static BattleSeaPlayer host = new BattleSeaPlayer();
    static BattleSeaPlayer guest =  new BattleSeaPlayer();

    public static void runBattleSea(){
        setupRandomBoard(host);
        host.playerGrid.printShips();
        //TODO ?????
    }

    private static void setupRandomBoard(BattleSeaPlayer battleSeaPlayer) {
        System.out.println();
        int counterOfShips = 0;

        Randomizer randomizer = new Randomizer();

        while (battleSeaPlayer.numOfShipsLeft() > 0) {
            for (Ship ship: battleSeaPlayer.ships) {
                int row = randomizer.nextInt(0, 9);
                int col = randomizer.nextInt(0, 9);
                int dir = randomizer.nextInt(0, 1);

                while (hasLocationError(row, col, dir, battleSeaPlayer, counterOfShips)) {
                    row = randomizer.nextInt(0, 9);
                    col = randomizer.nextInt(0, 9);
                    dir = randomizer.nextInt(0, 1);
                }

                battleSeaPlayer.ships[counterOfShips].setLocation(row, col);
                battleSeaPlayer.ships[counterOfShips].setDirection(dir);
                battleSeaPlayer.playerGrid.addShip(battleSeaPlayer.ships[counterOfShips]);

                counterOfShips++;
            }
        }
    }

    private static void askForGuess(BattleSeaPlayer player, BattleSeaPlayer opponent, int x, int y) {
        boolean flag = false;
        // row == y  ,  col == x

        if (x < 0 || x > 9 || y < 0 || y > 9) {
            System.out.println("Coordinates must be inside the grid.");
        }

        while (!flag) {
            if (!opponent.playerGrid.alreadyGuessed(y, x)){
                if (opponent.playerGrid.hasShip(y, x)) {
                    player.oppGrid.markHit(y, x);
                    opponent.playerGrid.markHit(y, x);
                    System.out.println("Bombed successfully!!!");
                    Ship oppShip = opponent.ships[convertLetterToInt(opponent.playerGrid.get(y, x).getShipName())];
                    Ship playerShip = player.ships[convertLetterToInt(player.oppGrid.get(y, x).getShipName())];
                    if (isShipDestroyCompletely(opponent, oppShip)) {
                        changeDestroyedShipSign(player, playerShip);
                        changeDestroyedShipSign(opponent, oppShip);
                    }



                } else {
                    player.oppGrid.markMiss(y, x);
                    opponent.playerGrid.markMiss(y, x);
                    System.out.println("Bombed unsuccessfully!");
                    flag = true;
                }
            } else {
                System.out.println("Selected X,Y has been already boomed");
            }
        }

    }

    private static boolean isShipDestroyCompletely (BattleSeaPlayer battleSeaPlayer, Ship ship) {
        int row = ship.getRow();
        int col = ship.getColumn();
        int length = ship.getLength();
        int width = ship.getWidth();
        int dir = ship.getDirection();
        boolean flag = false;

        if (dir == 0) {
            for (int i = row; i < row + width; i++) {
                for (int j = col; j < col + length; j++) {
                    if (battleSeaPlayer.playerGrid.get(i, j).checkHit()) {
                        flag = true;
                    }
                }
            }
        }

        // Vertical
        if (dir == 1) {
            for (int i = row; i < row + length; i++) {
                for (int j = col; j < col + width; j++) {
                    if (battleSeaPlayer.playerGrid.get(i, j).checkHit()) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    private static void changeDestroyedShipSign(BattleSeaPlayer battleSeaPlayer, Ship ship) {
        int row = ship.getRow();
        int col = ship.getColumn();
        int length = ship.getLength();
        int width = ship.getWidth();
        int dir = ship.getDirection();

        if (dir == 0) {
            for (int i = row; i < row + width; i++) {
                for (int j = col; j < col + length; j++) {
                    battleSeaPlayer.playerGrid.get(row, col).markAllHit();
                }
            }
        }

        // Vertical
        if (dir == 1) {
            for (int i = row; i < row + length; i++) {
                for (int j = col; j < col + width; j++) {
                    battleSeaPlayer.playerGrid.get(row, col).markAllHit();
                }
            }
        }
    }

    private static boolean hasLocationError(int row, int col, int dir, BattleSeaPlayer battleSeaPlayer, int counterOfShips) {
        int length = battleSeaPlayer.ships[counterOfShips].getLength();
        int width = battleSeaPlayer.ships[counterOfShips].getWidth();

        // Check if off grid - Horizontal
        if (dir == 0) {
            int checkLength = length + col;
            int checkWidth = width + row;
            if (checkLength > 10) {
                return true;
            }
            if (checkWidth > 10) {
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir == 1) {
            int checkLength = length + row;
            int checkWidth = width + col;
            if (checkLength > 10) {
                return true;
            }
            if (checkWidth > 10) {
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) {
            for (int i = row; i < row + width; i++) {
                for (int j = col; j < col + length; j++) {
                    if (battleSeaPlayer.playerGrid.hasShip(i, j)) {
                        return true;
                    }
                }
            }
        }

        // Vertical
        if (dir == 1) {
            for (int i = row; i < row + length; i++) {
                for (int j = col; j < col + width; j++) {
                    if (battleSeaPlayer.playerGrid.hasShip(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void changeShipsLocation(BattleSeaPlayer battleSeaPlayer, String shipName) {
        battleSeaPlayer.playerGrid.printShips();
        System.out.println();
        String input;

        input = shipName.toUpperCase();
        int selectedShip = convertLetterToInt(input);
        System.out.println("Enter new row: ");
        int newRow = scanner.nextInt();
        System.out.println("Enter new column: ");
        int newCol = scanner.nextInt();
        if (newCol >= 0 && newCol <= 9 && newRow >= 0 && newRow <= 9) {
            if (!hasLocationError(newRow, newCol, battleSeaPlayer.ships[selectedShip].getDirection(),
                    battleSeaPlayer, selectedShip)) {
                battleSeaPlayer.ships[selectedShip].setLocation(newRow, newCol);
                battleSeaPlayer.playerGrid.addShip(battleSeaPlayer.ships[selectedShip]);
                battleSeaPlayer.playerGrid.printShips();
                System.out.println("Ship " + input + " relocated " + "in (" + newRow + ", " + newCol + ") successfully!" );
             } else {
                System.out.println("Ship " + input + " can not relocate in (" + newRow + ", " + newCol + ").");
            }
        }
    }

    private static void changeShipsDirection(BattleSeaPlayer battleSeaPlayer, String shipName) {
        battleSeaPlayer.playerGrid.printShips();
        System.out.println();
        String input;

        input = shipName.toUpperCase();
        int selectedShip = convertLetterToInt(input);
        int row = battleSeaPlayer.ships[selectedShip].getRow();
        int col = battleSeaPlayer.ships[selectedShip].getColumn();
        int dir = battleSeaPlayer.ships[selectedShip].getDirection();
        if (dir == 0) {
            dir = 1;
        } else {
            dir = 0;
        }
        if (!hasLocationError(row, col, dir, battleSeaPlayer, selectedShip)) {
            battleSeaPlayer.ships[selectedShip].setLocation(row, col);
            battleSeaPlayer.ships[selectedShip].setDirection(dir);
            battleSeaPlayer.playerGrid.addShip(battleSeaPlayer.ships[selectedShip]);
            battleSeaPlayer.playerGrid.printShips();
            System.out.println("Ship " + input + " rotated successfully!" );
        } else {
            System.out.println("Ship " + input + " can not rotated.");
        }

    }

    private static int convertLetterToInt(String letter) {
        int toReturn = -1;
        switch (letter) {
            case "A":
                toReturn = 0;
                break;
            case "B":
                toReturn = 1;
                break;
            case "C":
                toReturn = 2;
                break;
            case "D":
                toReturn = 3;
                break;
            case "E":
                toReturn = 4;
                break;
            case "F":
                toReturn = 5;
                break;
        }
        return toReturn;
    }
}
