package pathfinding;

import environment.Point2D;

/**
 * Graph node, based on the A* heuristic with and added node traversal penalty.
 */
public abstract class Node implements Comparable<Node> {
    /**
     * Link to the latest selected node in the path search.
     */
    public Node parent;
    /**
     * Cost from the current node to the destiny node.
     */
    public int gCost;
    /**
     * Cost from the current node to the origin node.
     */
    public int hCost;

    /**
     * Returns the compounded cost.
     */
    public int fCost() {return gCost + hCost;}

    /**
     * Returns whether this node is traversable.
     */
    public abstract boolean isObstacle();

    /**
     * Returns the x position of the node.
     */
    //TODO: Aixo no hauria de ser aqui.
    public abstract int getX();
    public abstract Point2D location();

    /**
     * Returns the y position of the node.
     */
    //TODO: Aixo no hauria de ser aqui.
    public abstract int getY();
    //public abstract Point2D location();

    /**
     * Compares the cost of both nodes with preference to the fCost.
     */
    @Override
    public int compareTo(Node o) {
        int res = Integer.compare(fCost(), o.fCost());
        if (res != 0) return res;
        return Integer.compare(hCost, o.hCost);
    }

    /**
     * Returns the traversal penalty of this Node.
     */
    public abstract int penalty();
}
