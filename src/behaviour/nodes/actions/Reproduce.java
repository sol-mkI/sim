package behaviour.nodes.actions;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;
import environment.Point2D;
import utils.Utils;
import java.util.Arrays;
import java.util.List;

/**
 * Tries to create a new entity with the same class as its parent in a random neighbour node.
 */
public class Reproduce extends Leaf {

    private static final List<Point2D> targets = Arrays.asList(
        new Point2D(0, 1),
        new Point2D(0,-1),
        new Point2D(1,0),
        new Point2D(-1,0),
        new Point2D(1, 1),
        new Point2D(-1,-1),
        new Point2D(1,-1),
        new Point2D(-1,1)
    );

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println(state);
    }

    @Override
    public State onUpdate(Entity entity) {
        Point2D p = entity.position();
        p.add(Utils.random(targets));
        if (em.isPositionValid(p) && em.speciesInPosition(p, entity.specie()))
            em.createEntityFromString(entity.getClass().getSimpleName(), p);
        return State.SUCCESS;
    }
}
