package behaviour.nodes.conditionals;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;

public class IsTargetValid extends Leaf {
    @Override
    public void onStart() {
        System.out.println("Checking if Target is Valid");
    }

    @Override
    public void onStop() {
        System.out.println("Target is " + state);

    }

    @Override
    public State onUpdate() {
        return bb.target != null ? State.SUCCESS : State.FAILURE;
    }
}
