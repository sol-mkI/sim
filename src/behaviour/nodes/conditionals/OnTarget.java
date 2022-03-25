package behaviour.nodes.conditionals;

import behaviour.nodes.Leaf;
import behaviour.tree.State;

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
    public State onUpdate() {
        if (!owner.position().equals(bb.target))
            return State.FAILURE;
        return State.SUCCESS;
    }
}
