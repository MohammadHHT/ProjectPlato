package model.BattleSea;

import java.util.Scanner;

public class BattleSea {

    public static Scanner scanner = new Scanner(System.in);
    static BattleSeaPlayer host = new BattleSeaPlayer();
    static BattleSeaPlayer guest =  new BattleSeaPlayer();

    public static void runBattleSea(){
        setupRandomBoard(host);
        host.playerGrid.printShips();
        System.out.println("Do you like this arrangement of ships? (Y or N)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            System.out.println("Waiting for your opponent...");
        } else if (input.equalsIgnoreCase("N")) {
            changeShipsLocation(host);
            System.out.println("Waiting for your opponent...");
        } else {
            System.out.println("Invalid Command!");
        }
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

    private static void changeShipsLocation(BattleSeaPlayer battleSeaPlayer) {
        battleSeaPlayer.playerGrid.printShips();
        System.out.println();
        String input;
        do {
            System.out.print("Please select the ship you want...");
            input = scanner.nextLine();
            input = input.toUpperCase();
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
            System.out.println("Do you want to relocate another ship?(Y or N)");
            input = scanner.nextLine();
        } while (input.equalsIgnoreCase("Y"));
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
