package environment;

import entities.Entity;
import entities.animals.Animal;
import entities.vegetals.Vegetal;
import pathfinding.Node;
import pathfinding.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tile extends Node {

    private static final boolean DEBUG = true;

    private final Grid grid;
    private final ViewRenderer viewRenderer;
    private final TileContext tileContext;
    private final boolean walkable;
    private final Point2D position;


    private final List<Entity> entityList = new ArrayList<>();
    private final static int CAPACITY = 5;
    private int currentSize = 0;

    public Tile(ViewRenderer viewRenderer, Grid grid, boolean walkable, Point2D position, int size) {
        this.viewRenderer = viewRenderer;
        this.grid = grid;
        this.walkable = walkable;
        this.position = position;
        this.tileContext = new TileContext(position, size);
    }

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

    //region EntityManagement

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
        viewRenderer.remove(entity);
        currentSize -= entity.size();
    }
    public boolean addEntity(Entity entity) {
        if (canFit(entity)) {
            currentSize += entity.size();
            return entityList.add(entity);
        }
        return false;
    }
    public boolean moveEntity(Entity entity, Point2D target) {
        if (grid.tile(target).addEntity(entity)) {
            removeEntity(entity);
            entity.move(grid.tile(target));
            return true;
        }
        return false;
    }
    public boolean canFit(Entity entity) {
        return (currentSize + entity.size()) < CAPACITY;
    }



    //endregion
    //region Getters

    public Collection<? extends Entity> getEntities() {
        return entityList;
    }
    public Point2D location() {
        return position.getLocation();
    }

    @Override
    public int penalty() {
        // Makes pathfinder evade this node.
        return entityList.size() * 10;
        /*int penalty = 0;
        for (Entity entity : getEntities()) {
            penalty += entity instanceof Animal ? 1 : 0;
        }

        return penalty != 0 ? Integer.MAX_VALUE : 0;*/
    }

    public Grid grid() {
        return grid;
    }
    public boolean isObstacle() {
        return !walkable;
    }

    @Override
    public boolean canTraverse(Entity entity) {
        return canFit(entity);
    }

    @Override
    public int getX() {
        return position.x;
    }
    @Override
    public int getY() {
        return position.y;
    }
    public Pane getContext() {
        return tileContext;
    }

    //endregion
    //region Setters
    public void setColor(Color color) {
        tileContext.addColor(color);
    }
    //endregion
    //region Misc

    @Override
    public String toString() {
        return "Tile{" +
                "walkable=" + walkable +
                ", position=" + position +
                ", entityList=" + entityList +
                '}';
    }


    //endregion
}