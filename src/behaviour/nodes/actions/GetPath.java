package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;

/**
 * Returns a path from the position of an entity to a point in the environment.
 */
public class GetPath extends Leaf {

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.print(state);
    }

    @Override
    public State onUpdate(Entity entity) {
        if (bb.target == null)
            return State.FAILURE;

        if (bb.path != null &&
            !bb.path.isEmpty() &&
            bb.target.position().equals(bb.path.get(bb.path.size() - 1).location()))
            return State.SUCCESS;

        if (DEBUG) System.out.print(" NEW PATH ");
        bb.path = em.findPath(entity, bb.target.position());
        return bb.path != null ? State.SUCCESS : State.FAILURE;
    }
}
