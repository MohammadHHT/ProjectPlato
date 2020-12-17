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
    private boolean playerMovedInThisTurn = false;
    private int hostScore;
    private int guestScore;

    public int getHostScore() {
        return hostScore;
    }

    public int getGuestScore() {
        return guestScore;
    }

    static {
        dotsAndBoxes = new HashMap<>();
    }

    public boolean hasPlayerMovedInThisTurn() {
        return playerMovedInThisTurn;
    }

    public DotsAndBoxes(Player host) {
        super();
        dotsAndBoxes.put(getGameID(), this);
        edges = new ArrayList<>();
        this.host = host;
        turn = host;
    }

    public boolean isBoardFull() {
        return edges.size() == 112;
    }

    public Player getWinner() {
        if (hostScore > guestScore)
            return host;
        else if (guestScore > hostScore)
            return guest;
        else
            return null;
    }

    /*    private Player winner(int x1, int y1, int x2, int y2) {                             // executes just one time when edges size is 64 (board is full)
            Edge edge = new Edge(new Vertex(x1, y1), new Vertex(x2, y2), null);       // just for information full board has 112 edges :D
            for (Edge e : edges) {
                if (e.equals(edge)) {
                    return e.player;
                }
            }
            return null;
        }
    */
    public boolean isEdgeAvailable(int x1, int y1, int x2, int y2) {
        if (x1 < 1 || x1 > 8 || x2 < 1 || x2 > 8 || y1 < 1 || y1 > 8 || y2 < 1 || y2 > 8)
            return false;
        Edge temp = new Edge(new Vertex(x1, y1), new Vertex(x2, y2), null);
        for (Edge edge : edges) {
            if (edge.equals(temp))
                return false;
        }
        return true;
    }

    public boolean madeAnyBoxes(int x1, int y1, int x2, int y2) {
        boolean e1 = false, e2 = false, e3 = false, e4 = false, e5 = false, e6 = false;
        if (x1 == x2) {
            for (Edge edge : edges) {
                if (edge.equals(new Edge(new Vertex(x1, y1), new Vertex((x1 + 1), y1), null)))
                    e1 = true;
                else if (edge.equals(new Edge(new Vertex((x1 + 1), y1), new Vertex((x2 + 1), y2), null)))
                    e2 = true;
                else if (edge.equals(new Edge(new Vertex(x2, y2), new Vertex((x2 + 1), y2), null)))
                    e3 = true;
                else if (edge.equals(new Edge(new Vertex((x1 - 1), y1), new Vertex(x1, y1), null)))
                    e4 = true;
                else if (edge.equals(new Edge(new Vertex((x1 - 1), y1), new Vertex((x2 - 1), y2), null)))
                    e5 = true;
                else if (edge.equals(new Edge(new Vertex((x2 - 1), y2), new Vertex(x2, y2), null)))
                    e6 = true;
            }
        } else if (y1 == y2) {
            for (Edge edge : edges) {
                if (edge.equals(new Edge(new Vertex(x1, (y1 - 1)), new Vertex(x1, y1), null)))
                    e1 = true;
                else if (edge.equals(new Edge(new Vertex(x1, (y1 - 1)), new Vertex(x2, (y2 - 1)), null)))
                    e2 = true;
                else if (edge.equals(new Edge(new Vertex(x2, (y2 - 1)), new Vertex(x2, y2), null)))
                    e3 = true;
                else if (edge.equals(new Edge(new Vertex(x1, y1), new Vertex(x1, (y1 + 1)), null)))
                    e4 = true;
                else if (edge.equals(new Edge(new Vertex(x1, (y1 + 1)), new Vertex(x2, (y2 + 1)), null)))
                    e5 = true;
                else if (edge.equals(new Edge(new Vertex(x2, y2), new Vertex(x2, (y2 + 1)), null)))
                    e6 = true;
            }
        }
        if ((e1 && e2 && e3) || (e4 && e5 && e6)) {
            if ((e1 && e2 && e3) && (e4 && e5 && e6)) {
                if (turn.equals(host))
                    hostScore += 2;
                else
                    guestScore += 2;
            } else {
                if (turn.equals(host))
                    hostScore++;
                else
                    guestScore++;
            }
            return true;
        }
        playerMovedInThisTurn = true;
        return false;
    }

    public void occupy(int x1, int y1, int x2, int y2) {                                // no need to check that if edge is empty. this will be done in DotsAndBoxesMenu
        edges.add(new Edge(new Vertex(x1, y1), new Vertex(x2, y2), turn));
    }

    public String makeTable() {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Edge edge : edges) {
            stringBuilder.append("Line ").append(i).append(": ").append(edge.toString());
        }
        return stringBuilder.toString();
    }

    @Override
    public void turn() {                                                                // flips turn
        if (turn.equals(host)) {
            turn = guest;
        } else {
            turn = host;
        }
        playerMovedInThisTurn = false;
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

        @Override
        public String toString() {
            return "(" + v1.x + "," + v1.y + ") and (" + v2.x + "," + v2.y + ")";
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
