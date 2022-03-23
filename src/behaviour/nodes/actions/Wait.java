package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;

public class Wait extends Leaf {

    public long duration = 1;
    long startTime;

    @Override
    public void onStart() {
        startTime = System.nanoTime();
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        if (System.nanoTime() - startTime > duration * 1000000000)
            return State.SUCCESS;
        return State.RUNNING;
    }
}
