package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;

public class FollowPath extends Leaf {

    @Override
    public void onStart() {
        System.out.println("Following Path");
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        System.out.println("FollowPath state = " + owner.position() + "|" + bb.target);
        if (bb.path == null) return State.FAILURE;

        // add a loop if you want to traverse the entire path in one frame.

        if (!bb.path.isEmpty()) {
            owner.tile().moveEntity(owner, bb.path.remove(0));
            if (owner.position().equals(bb.target)) {
                bb.target = null;
                bb.path = null;
            }
            return State.SUCCESS;
        }
        return State.FAILURE;
    }
}
