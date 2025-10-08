package com.Tankgame.PathMaths;

public class Node implements Comparable<Node> {

    public int x, y;
    public int gCost, hCost, fCost;
    public Node parent;
    public boolean isWalkable;

    public Node(int x, int y, boolean isWalkable) {
        this.x = x;
        this.y = y;
        this.isWalkable = isWalkable;
    }

    public void calFCost() {
        fCost = gCost+hCost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(fCost, other.fCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
