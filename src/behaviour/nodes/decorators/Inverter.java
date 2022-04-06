package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.Decorator;
import entities.Entity;

/**
 * Reverts the state of the child node. SUCCESS <-> FAILURE
 */
public class Inverter extends Decorator {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate(Entity entity) {
        switch (child.update(entity)) {
            case RUNNING:
                return State.RUNNING;
            case FAILURE:
                return State.SUCCESS;
            case SUCCESS:
                return State.FAILURE;
        }
        return State.FAILURE;
    }
}
