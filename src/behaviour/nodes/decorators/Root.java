package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.Node;
import entities.Entity;

/**
 * Root node, only updates and passes the state of its child.
 */
public class Root extends Node {

    public Node child;

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate(Entity entity) {
        return child.update(entity);
    }
}
