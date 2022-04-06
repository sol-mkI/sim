package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import behaviour.tree.TestRabbitBehaviour;
import entities.Entity;
import entities.animals.Rabbit;
import environment.Point2D;

import java.util.Random;

/**
 * Finds a non-obstacle, walkable random point within the boundaries of the environment.
 */
public class GetRandomPoint extends Leaf {

    private Random random;

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
        random = new Random(System.nanoTime());
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.print(state);
    }

    @Override
    public State onUpdate(Entity entity) {
        Entity e = new Rabbit(new TestRabbitBehaviour(em));
        e.setPosition(getWalkablePoint(entity));
        bb.target = e;
        if (bb.target.position().equals(new Point2D()))
            return State.FAILURE;
        return State.SUCCESS;
    }

    private Point2D getWalkablePoint(Entity entity) {
        Point2D bounds = em.bounds();
        Point2D p = new Point2D();

        //TODO: FIX BOUNDS
//        int xLow = Math.max(pos.x - range, 0);
  //      int xHigh = Math.min(pos.x + range, size.x-1);
    //    int yLow = Math.max(pos.y - range, 0);
      //  int yHigh = Math.min(pos.y + range, size.y-1);

//        int xBound = random.nextInt(xHigh - xLow) + xLow;
  //      int yBound = random.nextInt(yHigh - yLow) + yLow;

        do {
            p.setLocation(random.nextInt(bounds.getX()-1), random.nextInt(bounds.getY() - 1));
        } while(!em.canMove(entity, p));

        return p;
    }
}
