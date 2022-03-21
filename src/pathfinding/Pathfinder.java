package pathfinding;

import java.util.*;

public class Pathfinder {
    private final Environment env;

    public Pathfinder(Environment env) {
        this.env = env;
    }

    public Collection<Point2D> findPath(Point2D o, Point2D t) {
        Node origin = env.getNode(o);
        Node target = env.getNode(t);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();
        openSet.add(origin);

        while (openSet.size() > 0) {
            Node Node = openSet.poll();
            closedSet.add(Node);

            if (Node == target)
                return tracePath(origin, target);

            for (Node neighbour : env.getNeighbours(Node)) {
                if (neighbour.isObstacle() || closedSet.contains(neighbour))
                    continue;

                int costToNeighbour = Node.gCost + getDistance(Node, neighbour);
                if (costToNeighbour < neighbour.gCost || !openSet.contains(neighbour)) {
                    neighbour.gCost = costToNeighbour;
                    neighbour.hCost = getDistance(neighbour, target);
                    neighbour.parent = Node;
                    openSet.add(neighbour);
                }
            }
        }
        return null;
    }

    private int getDistance(Node n1, Node n2) {
        int dx = Math.abs(n1.getX() - n2.getX());
        int dy = Math.abs(n1.getY() - n2.getY());

        if (dx > dy)
            return 14 * dy + 10 * (dx-dy);
        return 14 * dx + 10 * (dy-dx);
    }

    public Collection<Point2D> tracePath(Node origin, Node target) {
        List<Point2D> path = new ArrayList<>();
        Node actual = target;
        while (actual != origin) {
            path.add(actual.getLocation());
            actual = actual.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
