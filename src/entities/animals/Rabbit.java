package entities.animals;

import behaviour.tree.TestBehaviour;
import entities.Entity;
import entities.Species;
import environment.Tile;
import pathfinding.Point2D;

import java.util.*;

public class Rabbit extends Animal {

    private final Map<Species, Integer> priorityMap = new HashMap<>();
    private final TestBehaviour behaviour = new TestBehaviour(this);

    public Rabbit(Tile tile) {
        super(tile);
        priorityMap.put(Species.CARROT, 50); // carrot value
        consumables.add(Species.CARROT);
        species = Species.RABBIT;
    }

    @Override
    public void update() {
        super.update();
        behaviour.update();
    }

    @Override
    public void computePriority() {
        /*priorityList.clear();
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
        priorityList.sort(Collections.reverseOrder());*/
    }

    @Override
    public void eat(Entity target) {
        super.eat(target);
        tile.addEntity(new Rabbit(tile));
    }
}
