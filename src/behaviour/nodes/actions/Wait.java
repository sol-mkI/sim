package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;

/**
 * Waits for N ticks.
 */
public class Wait extends Leaf {

    public long duration;
    long c = 0;

    public Wait(long duration) {
        this.duration = duration;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate(Entity entity) {
        if (c++ == duration) {
            c = 0;
            return State.SUCCESS;
        }
        return State.FAILURE;
    }
}
