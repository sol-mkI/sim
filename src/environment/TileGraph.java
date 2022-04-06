package environment;

import exceptions.CoordinateNotValidException;
import javafx.scene.layout.Pane;
import pathfinding.Graph;
import pathfinding.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Abstracta amb la funcionalitat basica d'un entorn compost per caselles.
 */
public abstract class TileGraph implements Graph {
    /**
     * Pane where all tiles are instanced.
     */
    protected Pane context;
    /**
     * Matrix of tiles.
     */
    protected Tile[][] env;
    /**
     * X & Y bounds of the matrix.
     */
    protected final Point2D bounds;

    /**
     * Constructor with size parameter.
     */
    protected TileGraph(Point2D bounds) {this.bounds = bounds;}

    /**
     * Updates the state of every tile.
     */
    public void render() {
        for (Tile[] row : env)
            for (Tile tile : row)
                tile.render();
    }

    /**
     * Getter for the bounds of the graph (matrix).
     */
    public Point2D getBounds() {return bounds.getLocation();}

    /**
     * Checks whether q is inside the bounds of the matrix.
     * @param q Position
     */
    public boolean isPositionInBounds(Point2D q) {return (q.getX() >= 0 && q.getX() < bounds.getX()) && (q.getY() >= 0 && q.getY() < bounds.getY());}

    /**
     * Getter for the javafx context.
     */
    public Pane getContext() {return context;}

    /**
     * Getter for the graph data.
     */
    public Tile[][] getTiles() {return env;}


    /**
     * Getter for the tile at position p.
     * @param p Position
     */
    public Tile getTile(Point2D p) {
        if (!isPositionInBounds(p))
            throw new CoordinateNotValidException(p);
        return env[p.getX()][p.getY()];
    }

    /**
     * <Graph> Method Implementation
     * Getter for the node at position p.
     * @param p Position
     */
    public Node getNode(Point2D p) {
        if (!isPositionInBounds(p))
            throw new CoordinateNotValidException(p);
        return env[p.getX()][p.getY()];
    }
    /**
     * Returns the neighbouring nodes of a node.
     * @param node Node
     * @return Neighbours
     */
    public List<Tile> getNeighbours(Node node) {
        List<Tile> neighbours = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Point2D p = node.location();
                p.setLocation(p.getX() + i, p.getY() + j);
                if (isPositionInBounds(p))
                    neighbours.add( (Tile) getNode(p));
            }
        return neighbours;
    }
}
