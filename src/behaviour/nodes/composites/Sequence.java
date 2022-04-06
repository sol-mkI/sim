package behaviour.nodes.composites;

import behaviour.tree.State;
import behaviour.nodes.Composite;
import behaviour.nodes.Node;
import entities.Entity;

/**
 * Processes child nodes from 0..n until one fails and returns FAILURE or every child succeeds returning SUCCESS.
 */
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
    public State onUpdate(Entity entity) {
        Node child = children.get(current);

        switch (child.update(entity)) {
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
