package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;

/**
 * Follows a given path one step at a time.
 */
public class FollowPath extends Leaf {
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
        if (bb.path == null) return State.SUCCESS;

        return follow(entity);
    }

    private State follow(Entity entity) {
        if (bb.path.isEmpty() || bb.path.get(bb.path.size() - 1).location().equals(entity.position())) {
            bb.target = null;
            return State.SUCCESS;
        }

        if (DEBUG) System.out.println(bb.path);
        if (em.moveEntity(entity, bb.path.remove(0).location()))
            return State.SUCCESS;
        return State.FAILURE;
    }
}
