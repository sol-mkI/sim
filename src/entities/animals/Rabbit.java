package entities.animals;

import behaviour.tree.TestBehaviour;
import entities.Entity;
import entities.Species;
import environment.Tile;
import pathfinding.Point2D;
import utils.Utils;

import java.util.*;

public class Rabbit extends Animal {

    private final Map<Species, Integer> priorityMap = new HashMap<>();
    private final TestBehaviour behaviour = new TestBehaviour(this);

    public Rabbit(Tile tile) {
        super(tile);

        priorityMap.put(Species.CARROT, 50);
        priorityMap.put(Species.RABBIT, 10);// carrot value

        species = Species.RABBIT;
        size = 1;
        health = 100;
    }

    @Override
    public void update() {
        super.update();
        recieveDamage(1);
        behaviour.update();
    }

    @Override
    public void computePriority() {
        List<Entity> l = spiralCheck(15);
        for (Entity target : l) {
            int distance = (int)calculateDistance(target.position());
            if (distance != 0)
                subPQ.add(priorityMap.get(target.specie()) / distance);
            else
                subPQ.add(priorityMap.get(target.specie()));
        }

        for (int i = 0; i < PRIORITY_SIZE; i++)
            if (!subPQ.isEmpty())
                priority[i] = subPQ.poll();
            else
                priority[i] = 0;

    }

    @Override
    public boolean eat(Entity target) {
        super.eat(target);
        health += 25;
        Tile t = Utils.random(tile.grid().getNeighbours(tile));
        if (!t.isObstacle() && rand.nextDouble() < 0.5f)
            t.addEntity(new Rabbit(t));
        return true;
    }

    public int distance(int x1, int x2, int y1, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);
        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

    private List<Entity> spiralCheck(int range) {
        List<Entity> entityList = new LinkedList<>();

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
