package behaviour.nodes.decorators;

import behaviour.tree.State;
import behaviour.nodes.base.Decorator;

public class Inverter extends Decorator {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        switch (child.update()) {
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
