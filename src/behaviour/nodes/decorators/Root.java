package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.Node;

public class Root extends Node {

    public Node child;

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        return child.update();
    }
}
