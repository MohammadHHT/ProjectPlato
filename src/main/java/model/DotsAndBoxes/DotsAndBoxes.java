package model.DotsAndBoxes;

import model.Game;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DotsAndBoxes extends Game {
    private static HashMap<Long, DotsAndBoxes> dotsAndBoxes;

    private ArrayList<Edge> edges;
    private Player host;
    private Player guest;
    private Player turn;

    static {
        dotsAndBoxes = new HashMap<>();
    }

    public DotsAndBoxes(Player host) {
        super();
        dotsAndBoxes.put(getGameID(), this);
        edges = new ArrayList<>();
        this.host = host;
        turn = host;
    }

    private Player winner(int x1, int y1, int x2, int y2) {                             // executes just one time when edges size is 64 (board is full)
        Edge edge = new Edge(new Vertex(x1, y1), new Vertex(x2, y2), null);
        for (Edge e : edges) {
            if (e.equals(edge)) {
                return e.player;
            }
        }
        return null;
    }

    public void occupy(int x1, int y1, int x2, int y2) {                                // no need to check that if edge is empty. this will be done in DotsAndBoxesMenu
        edges.add(new Edge(new Vertex(x1, y1), new Vertex(x2, y2), turn));
    }

    @Override
    public void turn() {                                                                // flips turn
        if (turn.equals(host)) {
            turn = guest;
        } else {
            turn = host;
        }
    }

    @Override
    public boolean join(Player guest) {                                                  // another player joins and game can be start
        if (this.guest == null) {
            this.guest = guest;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Player judge() {                                                             // judges whole game when finished (in this one, board is full)
        return null;
    }

    public Player getTurn() {
        return turn;
    }

    public Player getHost() {
        return host;
    }

    public Player getGuest() {
        return guest;
    }

    public static HashMap<Long, DotsAndBoxes> getDotsAndBoxes() {
        return dotsAndBoxes;
    }

    class Edge {
        private Vertex v1;
        private Vertex v2;
        Player player;                                  // player: to set colors and make difference

        Edge(Vertex v1, Vertex v2, Player player) {
            this.v1 = v1;
            this.v2 = v2;
            this.player = player;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                Edge edge = (Edge) obj;
                return (v1.equals(edge.v1) && v2.equals(edge.v2));
            }
            return false;
        }
    }

    class Vertex {
        private int x;
        private int y;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Vertex) {
                Vertex vertex = (Vertex) obj;
                return (x == vertex.x && y == vertex.y);
            }
            return false;
        }
    }
}
