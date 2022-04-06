package pathfinding;

import java.util.Collection;

/**
 * Simple graph interface.
 */
public interface Graph {
    /**
     * Returns the neighbouring nodes of a node.
     * @param node Node
     * @return Neighbours
     */
    Collection<? extends Node> getNeighbours(Node node);
}
