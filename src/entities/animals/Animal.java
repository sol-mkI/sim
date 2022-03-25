package entities.animals;

import entities.Entity;
import entities.Species;
import environment.Tile;
import pathfinding.Point2D;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends Entity {

    protected final Point2D direction = new Point2D();
    protected Tile prevTile = null;

    protected Animal(Tile tile) {
        super(tile);
    }

    @Override
    public void update() {
        updateDirection();
    }

    protected double calculateDistance(Point2D target) {
        double ac = Math.abs(target.y - tile.getY());
        double cb = Math.abs(target.x - tile.getX());
        return Math.hypot(ac, cb);
    }

    private void updateDirection() {
        if (prevTile == null) prevTile = tile;
        direction.x = Integer.compare(prevTile.getX(), tile.getX());
        direction.y = Integer.compare(prevTile.getY(), tile.getY());
        prevTile = tile;
    }

}
