package main.back.game.SeaBattle;

import main.back.account.Player;
import main.back.game.Game;

import java.util.HashMap;

public class SeaBattle extends Game {

    private HashMap<Player, Grid> grids;

    public SeaBattle(Player host) {
        super(host);
        grids.put(host, new Grid());
    }

    public HashMap<Player, Grid> getGrids() {
        return grids;
    }

    @Override
    public void join(Player guest) {
        super.guest = guest;
        grids.put(guest, new Grid());
    }

    @Override
    public Player judge() {
        for (Grid g : grids.values()) {
            if (!g.equals(grids.get(turn)) && g.getDestroyed() == 22) {
                return turn;
            }
        }
        return null;
    }
}

