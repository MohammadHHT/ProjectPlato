package model.BattleSea;

public class Bomb {
    private int x;
    private int y;
    private boolean isDestroyed;

    public Bomb(int x, int y, boolean isDestroyed) {
        this.x = x;
        this.y = y;
        this.isDestroyed = isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public Bomb plantBomb(int x, int y, BattleSeaPlayer planter) {
        return null;
    }

    public boolean isDestroyed (BattleSeaPlayer planter) {
        return true;
    }

    public void checkState() {
        //TODO
    }
}
