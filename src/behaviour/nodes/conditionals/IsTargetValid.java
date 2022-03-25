package behaviour.nodes.conditionals;

import behaviour.tree.State;
import behaviour.nodes.Leaf;

public class IsTargetValid extends Leaf {
    @Override
    public void onStart() {
        if (DEBUG) System.out.println(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println(state);
    }

    @Override
    public State onUpdate() {
        if (bb.target == null)
            return State.FAILURE;
        return State.SUCCESS;
    }
}
