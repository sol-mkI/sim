package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.Decorator;
import entities.Entity;

/**
 * Repeatedly updates the child node regardless of state.
 */
public class Repeater extends Decorator {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate(Entity entity) {
        child.update(entity);
        return State.RUNNING;
    }
}
