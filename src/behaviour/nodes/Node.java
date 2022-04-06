package behaviour.nodes;

import behaviour.tree.Blackboard;
import behaviour.tree.State;
import entities.Entity;
import entities.EntityManager;

/**
 * Base node of a tree.
 */
public abstract class Node {

    protected static final boolean DEBUG = false;
    protected static final boolean DEBUG_COLOR = true;

    public State state = State.RUNNING;
    public boolean started = false;

    public Entity owner;
    public Blackboard bb;
    public EntityManager em;

    /**
     * Starts the node if not started, then updates it,
     * and when either success of failure happens, resets the node.
     * @return State
     */
    public State update(Entity entity) {
        if (!started) {
            onStart();
            started = true;
        }

        state = onUpdate(entity);

        if (state == State.FAILURE || state == State.SUCCESS) {
            onStop();
            started = false;
        }
        return state;
    }

    /**
     * Executed at the beginning of a node.
     */
    public abstract void onStart();

    /**
     * Executed at the end of a node.
     */
    public abstract void onStop();

    /**
     * Executed while the node is running.
     * @return State
     */
    public abstract State onUpdate(Entity entity);
}
