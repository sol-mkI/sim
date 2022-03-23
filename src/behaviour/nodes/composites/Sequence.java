package behaviour.nodes.composites;

import behaviour.tree.State;
import behaviour.nodes.base.Composite;
import behaviour.nodes.base.Node;

public class Sequence extends Composite {

    int current;

    @Override
    public void onStart() {
        current = 0;
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        Node child = children.get(current);
        switch (child.update()) {
            case RUNNING:
                return State.RUNNING;
            case FAILURE:
                return State.FAILURE;
            case SUCCESS:
                current++;
                break;
        }
        return current == children.size() ? State.SUCCESS : State.RUNNING;
    }
}
