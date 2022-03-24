package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;


// Follows a path, one step every iteration.
// Path must be valid.

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
    public State onUpdate() {
        if (bb.path == null || bb.path.isEmpty()) return State.SUCCESS;
       /* if (owner.position() == bb.target || owner.position() == bb.randomTarget) {
            bb.target = null;
        }*/

        if (owner.tile().moveEntity(owner, bb.path.remove(0)))
            return State.SUCCESS;
        return State.FAILURE;
    }
}
