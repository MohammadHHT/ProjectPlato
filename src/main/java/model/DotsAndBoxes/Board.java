package model.DotsAndBoxes;

import model.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    private static ArrayList<Board> boards = new ArrayList<>();

    private HashSet<Edge> edges;
    private Player host;
    private Player guest;
    private Player turn;

    Board(Player host) {
        edges = new HashSet<>();
        this.host = host;
        turn = host;
    }

    public void join(Player guest) {
        this.guest = guest;
    }

    public void changeTurn() {
        if (turn.equals(host)) {
            turn = guest;
        } else {
            turn = host;
        }
    }

    public Player showTurn() {
        return turn;
    }

    public boolean hasEdgeOcuppied(int x, int y) {
        for (Edge edge : edges) {
            if (edge.x == x && edge.y == y) {
                return true;
            }
        }
        return false;
    }

    public void occupyEdge(int x, int y, Player player, String dir) {
        if (dir.equals("horizontal")) {
            edges.add(new Edge(x, y, player, Dir.HORIZONTAL));
            judgePlace(x, y, Dir.HORIZONTAL);
        } else {
            edges.add(new Edge(x, y, player, Dir.VERTICAL));
            judgePlace(x, y, Dir.VERTICAL);
        }
    }

    public void occupyEdge() {}    //random

    private void judgePlace(int x, int y, Dir dir) {}

    private void judgeBoard() {}

    enum Dir {HORIZONTAL, VERTICAL};

    class Edge {
        int x;
        int y;
        Player player;
        Dir dir;

        Edge(int x, int y, Player player, Dir dir) {
            this.x = x;
            this.y = y;
            this.player = player;
            this.dir = dir;
        }
    }
}
