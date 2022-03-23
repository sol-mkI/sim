package simulation;

import entities.Entity;
import entities.animals.Animal;
import entities.animals.Rabbit;
import entities.vegetals.Carrot;
import entities.vegetals.Vegetal;
import environment.Grid;
import environment.Tile;
import environment.ViewRenderer;
import pathfinding.Point2D;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Simulation implements ViewRenderer {
    private final Grid grid = new Grid(this,1800,900,100,50);
    static PriorityQueue<Entity> pq = new PriorityQueue<>();

    public void populateSimulation(int i) {
        Point2D p;
        Tile tile;
        boolean rabbit = false;
        for (int j=0; j<i;j++) {
            do {
                p = new Point2D(
                        new Random(System.nanoTime()).nextInt(grid.getSize().x-1),
                        new Random(System.nanoTime()).nextInt(grid.getSize().y-1));
                tile = grid.tile(p);
            } while (tile.isObstacle() || tile.getEntities().stream().anyMatch(e -> e instanceof Vegetal));
            Entity entity = new Random(System.nanoTime()).nextDouble() < 1 ? new Carrot(tile) : new Rabbit(tile);
            if (!rabbit) {
                tile.addEntity(new Rabbit(tile));
                rabbit = true;
            }
            tile.addEntity(entity);
        }


    }
    long frame = 0;
    public void update() {
        long t0 = System.nanoTime();
        step();
        render();
        setupEntities();
        long t1 = System.nanoTime();

        AtomicInteger a = new AtomicInteger(0);
        AtomicInteger v = new AtomicInteger(0);
        pq.forEach(e -> {
            if (e instanceof Animal)
                a.getAndIncrement();
            if (e instanceof Vegetal)
                v.getAndIncrement();
        });
        System.out.println("F" + ++frame + " in " + (t1-t0)/1e6 + " ms.    " + a + " animals and " + v + " vegetals.");
    }

    @Override
    public void remove(Entity entity) {
        pq.remove(entity);
    }

    public void render() {
        grid.render();
    }

    private void setupEntities() {
        for (Entity entity : grid.getEntities()) {
            entity.computePriority();
            pq.offer(entity);
        }
    }

    private void step() {
        while (!pq.isEmpty())
            pq.poll().update();
    }

    public Grid getGrid() {return grid;}

}
