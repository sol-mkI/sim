package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;

/**
 * Logs a message and returns SUCCESS.
 */
public class Log extends Leaf {
    public String message;
    @Override
    public void onStart() {
        if (DEBUG) System.out.println("OnStart{" + message + "}");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println("OnStop{" + message + "}");
    }

    @Override
    public State onUpdate(Entity entity) {
        System.out.println("OnUpdate{" + message + "}");
        return State.SUCCESS;
    }
}
