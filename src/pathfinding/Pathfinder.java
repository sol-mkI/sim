package pathfinding;

import java.util.*;

/**
 * Graph traversal and path search algorithm A*
 */
public class Pathfinder {
    private final Graph graph;

    /**
     * Constructor with a graph.
     */
    public Pathfinder(Graph graph) {
        this.graph = graph;
    }

    /**
     * Aims to find a path to the destiny node having the
     * smallest cost (least distance travelled, travel trough faster areas...).
     *
     * @param origin Node where the search starts.
     * @param destiny Node where the search ends.
     * @return List of nodes.
     */
    public List<Node> findPath(Node origin, Node destiny) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(origin);

        while (openSet.size() > 0) {
            Node Node = openSet.poll();
            closedSet.add(Node);

            if (Node == destiny)
                return tracePath(origin, destiny);

            for (Node neighbour : graph.getNeighbours(Node)) {
                if (neighbour.isObstacle() || closedSet.contains(neighbour) /*|| !neighbour.canTraverse(e)*/)
                    continue;

                int costToNeighbour = Node.gCost + getDistance(Node, neighbour) + neighbour.penalty();
                if (costToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = costToNeighbour;
                    neighbour.hCost = getDistance(neighbour, destiny);
                    neighbour.parent = Node;
                    openSet.add(neighbour);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * Returns the distance between two nodes.
     */
    private static int getDistance(Node n1, Node n2) {
        // Això hauria de ser abstracte, ja que cada implementacio
        // de graf pot tenir diferents metodes de calcular la distància.
        // Aquesta només funciona per matrius.
        int dx = Math.abs(n1.getX() - n2.getX());
        int dy = Math.abs(n1.getY() - n2.getY());

        if (dx > dy)
            return 14 * dy + 10 * (dx-dy);
        return 14 * dx + 10 * (dy-dx);
    }

    /**
     * Traces and returns a path from the origin to the destiny.
     * @param origin Node
     * @param target Node
     * @return path
     */
    private static List<Node> tracePath(Node origin, Node target) {
        List<Node> path = new ArrayList<>();
        Node actual = target;
        while (actual != origin) {
            path.add(actual);
            actual = actual.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
