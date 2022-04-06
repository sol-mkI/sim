package behaviour.nodes.conditionals;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;

/**
 * Checks whether a target exists.
 */
public class DoesTargetExist extends Leaf {
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
        if (bb.target == null)
            return State.FAILURE;
        return State.SUCCESS;
    }
}
