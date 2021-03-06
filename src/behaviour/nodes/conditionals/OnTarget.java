package behaviour.nodes.conditionals;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;

/**
 * Checks whether the owner entity is at the target position.
 */
public class OnTarget extends Leaf {
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
        if (bb.target == null) return State.FAILURE;
        if (!entity.position().equals(bb.target.position()))
            return State.FAILURE;
        return State.SUCCESS;
    }
}
