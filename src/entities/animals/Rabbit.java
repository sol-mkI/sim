package entities.animals;

import entities.Species;
import environment.Tile;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.random;

public class Rabbit extends Animal {

    public Rabbit(Tile tile) {
        super(tile);
        consumables.add(Species.CARROT);
        species = Species.RABBIT;
    }

    @Override
    public void update() {
        super.update();

        move(t);
    }

    //TODO: Move to Action FollowPath
    List<Point2D> path;
    private final Point2D t = new Point2D(25,12);

    void move(Point2D p) {
        if(tile.getLocation().equals(p) || path == null) {
            generateNewTarget();
            path = new LinkedList<>(tile.grid().requestPath(tile.getLocation(), p));
            return;
        }

        if (DEBUG)
            for (Point2D step : path)
                if (step != path.get(0))
                    tile.grid().tile(step).setColor(Color.AQUA);

        if (!path.isEmpty()) {
            Point2D target = path.remove(0);
            tile.moveEntity(this, target);
            tile = tile.grid().tile(target);

            sensor.move(tile, direction);
        }
    }

    private void generateNewTarget() {
        do {
            t.move((int)(random() * tile.grid().getSize().x - 1), (int)(random() * tile.grid().getSize().y - 1));
        } while(tile.grid().tile(t).isObstacle());
    }
}
