package environment;

import entities.Entity;
import entities.vegetals.Vegetal;
import pathfinding.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A container that keeps a collection of entities in a position.
 */
public class Tile extends Node {

    private static final boolean DEBUG = true;

    /**
     * JavaFx context
     */
    private final TileContext tileContext;
    /**
     * If any entity can get inside this tile.
     */
    private final boolean walkable;
    /**
     * Position of the tile within an environment.
     */
    private final Point2D position;

    /**
     * Collection of entities in this tile.
     */
    private final List<Entity> entityList = new ArrayList<>();
    /**
     * Max entity load that a tile can hold.
     */
    private final static int CAPACITY = 5;
    /**
     * Current entity load.
     */
    private int currentSize = 0;

    /**
     * Constructor with parameters.
     */
    public Tile(boolean walkable, Point2D position, int size) {
        this.walkable = walkable;
        this.position = position;
        this.tileContext = new TileContext(position, size);
    }

    /**
     * If there is any change in the tile, add a color to the context.
     */
    public void render() {
        if (isObstacle())
            tileContext.addColor(Color.LIGHTSLATEGREY);

        if (DEBUG)
            if (!entityList.isEmpty()) {
                if (entityList.stream().noneMatch(e -> e instanceof Vegetal)) {
                    if (entityList.size() == 1)
                        tileContext.addColor(Color.CORNFLOWERBLUE);
                    else if (entityList.size() == 2)
                        tileContext.addColor(Color.ROYALBLUE);
                    else if (entityList.size() == 3)
                        tileContext.addColor(Color.DARKSLATEBLUE);
                    else tileContext.addColor(Color.VIOLET);
                } else {
                    tileContext.addColor(Color.DARKSALMON);

                }
            }

        tileContext.setColor(Color.LIGHTGREY);
    }

    /**
     * Removes an entity from this tile.
     */
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
        currentSize -= entity.size();
    }

    /**
     * Tries to add an entity to this tile.
     */
    public void addEntity(Entity entity) {
        if (!canFit(entity)) return;
        currentSize += entity.size();
        entityList.add(entity);
    }

    /**
     * Pathfinding penalty to traverse this tile.
     * @return amount
     */
    @Override
    public int penalty() {
        // Makes pathfinder evade this node if entities present.
        return entityList.size() * 10;
    }

    /**
     * Returns the location of this tile.
     */
    public Point2D location() {
        return position.getLocation();
    }

    /**
     * Checks whether an entity can fit inside this tile.
     */
    public boolean canFit(Entity entity) {return (currentSize + entity.size()) < CAPACITY;}

    /**
     * Checks whether this tile is traversable.
     */
    public boolean isObstacle() {
        return !walkable;
    }

    /**
     * Returns x coordinate of this tile
     */
    @Override
    public int getX() {return position.getX();}

    /**
     * Returns y coordinate of this tile.
     */
    @Override
    public int getY() {return position.getY();}

    /**
     * Getter for the javafx context of this tile.
     */
    public Pane getContext() {return tileContext;}

    /**
     * Enqueues a color to this tile.
     */
    public void setColor(Color color) {tileContext.addColor(color);}

    /**
     * Returns a list with the entities of this tile.
     */
    public List<Entity> getEntities() {return entityList;}
}