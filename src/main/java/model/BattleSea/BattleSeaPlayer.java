package model.BattleSea;

import model.Player;

public class BattleSeaPlayer {
    private static final int[] shipsLength = {2, 2, 3, 4, 4, 5};
    private static final int[] shipsWidth = {1, 1, 1, 1, 1, 2};
    private static final String[] shipsName = {"A", "B", "C", "D", "E", "F"};
    private static final int numOfShips = 6;

    public Ship[] ships;
    public Grid playerGrid;
    private Player player;


    public BattleSeaPlayer(Player player) {
        if (numOfShips != 6)
            throw new IllegalArgumentException("ERROR! Num of ships must be 6.");
        ships = new Ship[numOfShips];
        for (int i = 0; i < numOfShips; i++) {
            Ship tempShip = new Ship(shipsName[i], shipsLength[i], shipsWidth[i]);
            ships[i] = tempShip;
        }
        playerGrid = new Grid();
        this.player = player;
    }

    public int numOfShipsLeft() {
        int counter = 6;
        for (Ship ship : ships) {
            if (ship.isLocationSet() && ship.isDirectionSet())
                counter--;
        }
        return counter;
    }

    public void chooseShipLocation(Ship ship, int row, int column, int direction) {
        ship.setLocation(row, column);
        ship.setDirection(direction);
        playerGrid.addShip(ship);
    }
}
