package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.Decorator;

public class Repeater extends Decorator {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        child.update();
        return State.RUNNING;
    }
}
