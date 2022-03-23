package behaviour.nodes.base;

import behaviour.tree.Blackboard;
import behaviour.tree.State;
import entities.Entity;

public abstract class Node {

    protected static final boolean DEBUG = true;

    public State state = State.RUNNING;
    public boolean started = false;
    public Blackboard bb;
    public Entity owner;

    public State update() {
        if (!started) {
            onStart();
            started = true;
        }

        state = onUpdate();

        if (state == State.FAILURE || state == State.SUCCESS) {
            onStop();
            started = false;
        }
        return state;
    }

    public abstract void onStart();
    public abstract void onStop();
    public abstract State onUpdate();
}
