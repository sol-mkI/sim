package simulation;

import entities.Entity;
import entities.animals.Rabbit;
import entities.vegetals.Carrot;
import entities.vegetals.Vegetal;
import environment.Grid;
import environment.Tile;
import environment.ViewRenderer;
import pathfinding.Point2D;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulation implements ViewRenderer {
    private final Grid grid = new Grid(this,1800,900,100,50);
    static PriorityQueue<Entity> pq = new PriorityQueue<>();

    public void populateSimulation(int i) {
        Point2D p;
        Tile tile;
        for (int j=0; j<i;j++) {
            do {
                p = new Point2D(
                        new Random(System.nanoTime()).nextInt(grid.getSize().x-1),
                        new Random(System.nanoTime()).nextInt(grid.getSize().y-1));
                tile = grid.tile(p);
            } while (!tile.isObstacle() && tile.getEntities().stream().noneMatch(e -> e instanceof Vegetal));
            Entity entity = new Random(System.nanoTime()).nextDouble() < 0.5 ? new Carrot(tile) : new Rabbit(tile);
            tile.addEntity(entity);
        }
    }
    public void update() {
        render();
        setupEntities();
        step();
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
