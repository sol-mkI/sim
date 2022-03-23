package behaviour.nodes.conditionals;

import behaviour.nodes.base.Leaf;
import behaviour.tree.State;

public class IsTargetReached extends Leaf {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        if (bb.target == null) {
            System.out.println("Can't check if target is reached if there is no target");
            return State.FAILURE;
        }
        if (owner.position().equals(bb.target)) return State.SUCCESS;
        return State.FAILURE;
    }
}
