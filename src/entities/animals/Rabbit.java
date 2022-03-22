package entities.animals;

import behaviour.BehaviourTree;
import behaviour.TestBehaviour;
import entities.Entity;
import entities.Species;
import environment.Tile;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

import java.util.*;

import static java.lang.Math.random;

public class Rabbit extends Animal {

    private final Map<Species, Integer> priorityMap = new HashMap<>();
    private final TestBehaviour behaviour = new TestBehaviour(this);

    public Rabbit(Tile tile) {
        super(tile);
        priorityMap.put(Species.CARROT, 50); // carrot value
        consumables.add(Species.CARROT);
        //consumables.add(Species.RABBIT);
        species = Species.RABBIT;
    }

    @Override
    public void update() {
        super.update();
        behaviour.update();

        //move(t);
    }

    //TODO: Move to Action FollowPath
    List<Point2D> path;
    private final Point2D t = new Point2D(25,12);

    /*void move(Point2D p) {
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
    }*/

    private void generateNewTarget() {
        do {
            t.move((int)(random() * tile.grid().getSize().x - 1), (int)(random() * tile.grid().getSize().y - 1));
        } while(tile.grid().tile(t).isObstacle());
    }


    @Override
    public void computePriority() {
        priorityList.clear();
        List<Entity> targets = sensor.sense(consumables);
        for (Entity target : targets) {
            int distance = (int)calculateDistance(target.position());
            if (distance != 0) {
                priorityList.add(priorityMap.get(target.specie()) / distance);
                //if (DEBUG) System.out.println(">>> Distance to entity " + target + " is  " + distance);
            } else {
                priorityList.add(priorityMap.get(target.specie()));
            }
        }
        priorityList.sort(Collections.reverseOrder());
    }
}
