package pathfinding;
import java.util.Collection;

public interface Environment {
    Node getNode(Point2D p);
    Collection<? extends Node> getNeighbours(Node node);
}
