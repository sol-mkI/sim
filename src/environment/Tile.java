package environment;

import entities.Entity;
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
                    tileContext.addColor(Color.CORNFLOWERBLUE);
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
    }

    public void addEntity(Entity entity) {
        // no limit of entities right now
        entityList.add(entity);
    }
    //endregion
    //region Getters
    public Collection<? extends Entity> getEntities() {
        return entityList;
    }

    public Point2D getLocation() {
        return position.getLocation();
    }

    public Grid grid() {
        return grid;
    }

    public boolean isObstacle() {
        return !walkable;
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
                "position=" + position +
                ", entityList=" + entityList +
                '}';
    }

    public void moveEntity(Entity entity, Point2D target) {
        //entityList.remove(entity);
        removeEntity(entity);
        grid.tile(target).addEntity(entity);
    }

    //endregion
}