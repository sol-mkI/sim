package entities.animals;

import behaviour.tree.TestBehaviour;
import entities.Entity;
import entities.Species;
import environment.Tile;
import javafx.util.Pair;
import pathfinding.Point2D;
import utils.Utils;

import java.util.*;

public class Rabbit extends Animal {

    private final Map<Species, Integer> priorityMap = new HashMap<>();
    private final TestBehaviour behaviour = new TestBehaviour(this);

    public Rabbit(Tile tile) {
        super(tile);
        priorityMap.put(Species.CARROT, 50);
        priorityMap.put(Species.RABBIT, 0);// carrot value
        consumables.add(Species.CARROT);
        species = Species.RABBIT;
        size = 1;
        health = 200;
    }

    @Override
    public void update() {
        super.update();
        recieveDamage(1);
        behaviour.update();
    }

    @Override
    public void computePriority() {

        priorityList.clear();
        priorityList.add(0);

        /*List<Entity> l = spiralCheck(50);
        for (Entity target : l) {
            int distance = (int)calculateDistance(target.position());
            if (distance != 0) {
                priorityList.add(priorityMap.get(target.specie()) / distance);
            } else {
                priorityList.add(priorityMap.get(target.specie()));
            }
        }

        priorityList.sort(Collections.reverseOrder());*/
    }

    @Override
    public void eat(Entity target) {
        super.eat(target);
        health += 100;
        Tile t = Utils.random(tile.grid().getNeighbours(tile));
        if (!t.isObstacle())
            t.addEntity(new Rabbit(t));
    }

    public int distance(int x1, int x2, int y1, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);
        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

    private List<Entity> spiralCheck(int range) {
        List<Entity> entityList = new ArrayList<>();

        Point2D o = position();

        for (int i = o.x - range; i < o.x + range; i++)
            for (int j = o.y - range; j < o.y + range; j++) {

                int distance = distance(o.x, i, o.y, j);
                if (distance > range)
                    continue;

                if (!tile().grid().isCoordValid(i,j))
                    continue;

                entityList.addAll(tile().grid().tile(i, j).getEntities());
            }

        return entityList;
    }
}
