package pathfinding;

public abstract class Node implements Comparable<Node> {
    public Node parent;
    public int gCost;
    public int hCost;
    public int fCost() {return gCost + hCost;}

    public abstract boolean isObstacle();
    public abstract int getX();
    public abstract int getY();
    public abstract Point2D getLocation();

    @Override
    public int compareTo(Node o) {
        int res = Integer.compare(fCost(), o.fCost());
        if (res != 0) return res;
        return Integer.compare(hCost, o.hCost);
    }
}
