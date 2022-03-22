package entities;

import java.util.HashMap;
import java.util.List;

import environment.Grid;
import environment.Tile;
import javafx.util.Pair;
import pathfinding.Point2D;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

//TODO: InProgress

public class Sensor {
    private final Grid grid;
    private Tile origin;
    private final Point2D direction = new Point2D();
    private final int sightDistance;
    private final int sightRadius;
    private final int visionAngle;

    public Sensor(Grid grid, Tile origin, int sightDistance, int sightRadius, int visionAngle) {
        this.grid = grid;
        this.origin = origin;
        this.sightDistance = sightDistance;
        this.sightRadius = sightRadius;
        this.visionAngle = visionAngle;
    }

    public void move(Tile tile, Point2D dir) {
        origin = tile;
        direction.move(dir.x, dir.y);
    }

    private int distance(Point2D a, Point2D b) {
        int dx = Math.abs(b.x - a.x);
        int dy = Math.abs(b.y - a.y);

        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);

        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

    public List<Entity> sense(List<Species> species) {
        //List<Entity> entityList = new ArrayList<>();
        /*if (direction.x == -1 && direction.y == 0)
            entityList = senseHorizontal(species, 1);// right
        else if (direction.x == 1 && direction.y == 0)
            entityList = senseHorizontal(species, -1);// left
        else if (direction.x == 0 && direction.y == -1)
            entityList = senseVertical(species, 1); // down
        else if (direction.x == 0 && direction.y == 1)
            entityList = senseVertical(species, -1); // up
        else if (direction.x == -1 && direction.y == -1)
            entityList = senseDiagonal(species, 1, 1); // down right
        else if (direction.x == -1 && direction.y == 1)
            entityList = senseDiagonal(species, 1, -1); // up right
        else if (direction.x == 1 && direction.y == -1)
            entityList = senseDiagonal(species, -1, 1); // down left
        else if (direction.x == 1 && direction.y == 1)
            entityList = senseDiagonal(species, -1, -1); // up left*/

        //entityList = senseArea(species, origin.getLocation());
        return null;
    }

    private List<Entity> senseArea(List<Species> species, Point2D p) {
        List<Entity> entityList = new ArrayList<>();
        for (int i = p.x - sightRadius; i < p.x + sightRadius; i++)
            for (int j = p.y - sightRadius; j < p.y + sightRadius; j++) {
                Point2D k = new Point2D(i, j);
                checkPoint(species, entityList, p, k, sightRadius);
            }
        return entityList;
    }

    private void checkPoint(List<Species> species, List<Entity> entityList, Point2D p, Point2D k, int distance) {
        if (grid.isCoordValid(k)) {
            if (distance(p, k) < distance) {
                Tile tile = grid.tile(k);
                tile.setColor(Color.DARKKHAKI);
                for (Entity entity : tile.getEntities())
                    if (species.contains(entity.getSpecies()))
                        entityList.add(entity);
            }
        }
    }

    public List<Entity> senseHorizontal(List<Species> species, int mod) {
        List<Entity> entityList = new ArrayList<>();
        int ys = 1;
        for (int i = 0; i < sightDistance; i++) {
            for (int j = -ys; j <= ys; j++) {
                Point2D p = new Point2D(origin.getLocation().x+i*mod, origin.getLocation().y+j);
                checkPoint(species, entityList, origin.getLocation(), p, sightDistance);
            }
            if (i%Math.abs(visionAngle) == 0)
                if (visionAngle < 0) ys+=2;
                else ys++;
            ys = Math.min(sightDistance, ys);
        }
        return entityList;
    }
    public List<Entity> senseVertical(List<Species> species, int mod) {
        List<Entity> entityList = new ArrayList<>();
        int ys = 1;
        for (int i = 0; i < sightDistance; i++) {
            for (int j = -ys; j <= ys; j++) {
                Point2D k = origin.getLocation();
                Point2D p = new Point2D(k.x+j, k.y+i*mod);
                checkPoint(species, entityList, k, p, sightDistance);
            }
            if (i%Math.abs(visionAngle) == 0)
                if (visionAngle < 0) ys+=2;
                else ys++;
            ys = Math.min(sightDistance, ys);
        }
        return entityList;
    }
    public List<Entity> senseDiagonal(List<Species> species, int mod1, int mod2) {
        int distance = 2;
        Point2D p = origin.getLocation();
        List<Entity> entityList = new ArrayList<>();

        for (int h = 0; h < sightDistance/2 -1; h++) {
            for (int i = 0; i < distance; i++) {
                checkPoint(species, entityList, p, new Point2D(p.x + i*mod1, p.y), (int) (sightDistance /1.4));
                checkPoint(species, entityList, p, new Point2D(p.x, p.y + i*mod2), (int) (sightDistance /1.4));
            }
            distance++;
            distance = Math.min(distance, 9);
            p.move(p.x+mod1, p.y+mod2);
        }
        p.move(p.x+mod1*3, p.y+mod2*3);
        entityList.addAll(senseArea(species, p));
        return entityList;
    }
}
