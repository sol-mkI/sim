package environment;

import entities.Entity;
import exceptions.CoordinateNotValidException;
import javafx.scene.layout.Pane;
import pathfinding.Environment;
import pathfinding.Node;
import pathfinding.Pathfinder;
import pathfinding.Point2D;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Grid implements Environment {

    private final Point2D size;
    private final int nodeSize;
    private final Tile[][] grid;
    private final Pane context;
    private final Pathfinder pathfinder = new Pathfinder(this);


    public Grid(ViewRenderer viewRenderer, int x, int y, int nx, int ny) {
        size = new Point2D(nx,ny);
        grid = new Tile[size.x][size.y];
        nodeSize = Math.max(1, x/nx);

        context = new Pane();
        context.setPrefSize(x, y);
        generateTiles(viewRenderer);
    }
    private void generateTiles(ViewRenderer viewRenderer) {
        for (int i = 0; i < size.x; i++) {
            for (int j = 0; j < size.y; j++) {
                Tile tile = new Tile(viewRenderer, this,new Random(System.nanoTime()).nextDouble() > 0.2, new Point2D(i,j), nodeSize);
                grid[i][j] = tile;
                context.getChildren().add(grid[i][j].getContext());
            }
        }
    }

    public void render() {
        for (Tile[] row : grid)
            for (Tile tile : row)
                tile.render();
    }


    //region Pathfinding
    public List<Point2D> requestPath(Point2D o, Point2D t) {
        return pathfinder.findPath(o,t);
    }
    //endregion
    //region Getters

    public Point2D getSize() {return size;}
    public Node getNode(Point2D p) {
        if (!isCoordValid(p))
            throw new CoordinateNotValidException(p);
        return grid[p.x][p.y];
    }
    public Collection<Tile> getNeighbours(Node node) {
        List<Tile> neighbours = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;
                Point2D p = node.getLocation();
                p.move(p.x + i, p.y + j);
                if (0 <= p.x && p.x < size.x && 0 <= p.y && p.y < size.y)
                    neighbours.add(tile(p));
            }
        return neighbours;
    }
    public Collection<? extends Entity> getEntities() {
        Collection<Entity> res = new ArrayList<>();
        for (Tile[] row : grid)
            for (Tile tile : row)
                res.addAll(tile.getEntities());
        return res;
    }
    public Tile tile(Point2D p) {
        return grid[p.x][p.y];
    }
    public Tile tile(int x, int y) {
        return grid[x][y];
    }
    public Pane getContext() {
        return context;
    }
    public boolean isCoordValid(Point2D p) {
        return 0 <= p.x && p.x < size.x && 0 <= p.y && p.y < size.y;
    }
    public boolean isCoordValid(int x, int y) {
        return 0 <= x && x < size.x && 0 <= y && y < size.y;
    }

    //endregion
    //region Setters
    //endregion
}