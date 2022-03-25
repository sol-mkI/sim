package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;

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
    public State onUpdate() {
        System.out.println("OnUpdate{" + message + "}");
        return State.SUCCESS;
    }
}
