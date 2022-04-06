package behaviour.nodes.composites;

import behaviour.tree.State;
import behaviour.nodes.Composite;
import behaviour.nodes.Node;
import entities.Entity;

/**
 * Processes child nodes from 0..n until one succeeds and returns SUCCESS or every child fails returning FAILURE.
 */
public class Selector extends Composite {
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
            case SUCCESS:
                return State.SUCCESS;
            case FAILURE:
                current++;
                break;
        }
        return current == children.size() ? State.FAILURE : State.RUNNING;
    }
}
