package simulation;

import entities.Entity;
import entities.EntityManager;
import entities.animals.Animal;
import entities.vegetals.Vegetal;
import environment.TileGraph;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Updates a collection of entities and renders the environment.
 */
public class SimulationManager {
    private final TileGraph env;
    private final EntityManager em;

    /**
     * Constructor
     */
    public SimulationManager(TileGraph env, EntityManager em) {
        this.env = env;
        this.em = em;
    }

    /**
     * Frame counter.
     */
    private int frame = 0;

    /**
     * Retrieves entities and updates them sequentially in order.
     * Extra: print frame count + animal/vegetal count.
     */
    public void update() {
        PriorityQueue<Entity> pq = em.preloadEntities();

        AtomicInteger a = new AtomicInteger(0);
        AtomicInteger v = new AtomicInteger(0);
        pq.forEach(e -> {
            if (e instanceof Animal)
                a.getAndIncrement();
            if (e instanceof Vegetal)
                v.getAndIncrement();
        });

        long t0 = System.nanoTime();
        while (!pq.isEmpty())
            pq.poll().update();

        env.render();

        long t1 = System.nanoTime();
        System.out.println("F" + ++frame + " in " + (t1-t0)/1e6 + " ms.    " + a + " animals and " + v + " vegetals.");
    }
}
