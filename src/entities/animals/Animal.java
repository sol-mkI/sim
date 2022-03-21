package entities.animals;

import entities.Entity;
import entities.Sensor;
import entities.Species;
import environment.Tile;
import pathfinding.Point2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends Entity {

    private Tile prevTile = null;

    protected List<Species> consumables = new ArrayList<>();
    protected final Sensor sensor;
    protected final Point2D direction = new Point2D();

    protected Animal(Tile tile) {
        super(tile);
        sensor = new Sensor(tile.grid(), tile, 20, 5, 3);
    }

    @Override
    public void update() {
        updateDirection();
        sensor.sense(consumables);
    }

    private void updateDirection() {
        if (prevTile == null) prevTile = tile;
        direction.x = Integer.compare(prevTile.getX(), tile.getX());
        direction.y = Integer.compare(prevTile.getY(), tile.getY());
        prevTile = tile;
    }

}
