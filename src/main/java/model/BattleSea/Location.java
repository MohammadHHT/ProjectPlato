package model.BattleSea;

public class Location {

    public static final int UN_GUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;
    public static final int ALL_HIT = 3;

    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private String shipName;
    private int directionOfShip;

    public Location() {
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        shipName = " ";
        directionOfShip = -1;
    }

    public boolean checkHit() {
        return status == HIT;
    }

    public boolean checkMiss() {
        return status == MISSED;
    }

    public boolean isUnGuessed() {
        return status == UN_GUESSED;
    }

    public boolean isAllHit() {
        return status == ALL_HIT;
    }

    public void markHit() {
        setStatus(HIT);
    }

    public void markMiss() {
        setStatus(MISSED);
    }

    public boolean hasShip() {
        return hasShip;
    }

    public void setShip(boolean val) {
        this.hasShip = val;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getLengthOfShip() {
        return lengthOfShip;
    }

    public void setLengthOfShip(int val) {
        lengthOfShip = val;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipName() {
        return shipName;
    }

    public int getDirectionOfShip() {
        return directionOfShip;
    }

    public void setDirectionOfShip(int val) {
        directionOfShip = val;
    }
}
