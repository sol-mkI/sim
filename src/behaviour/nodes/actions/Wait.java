package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;

public class Wait extends Leaf {

    public long duration = 1;
    //long startTime = System.nanoTime();
    long c = 0;

    public Wait(long duration) {
        this.duration = duration;
    }

    @Override
    public void onStart() {
        //startTime = System.nanoTime();
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {

        if (c++ == duration) {
            c = 0;
            return State.SUCCESS;
        }
        return State.FAILURE;

        /*if (System.nanoTime() - startTime > duration * 1000000000)
            return State.SUCCESS;
        return State.RUNNING;*/
    }
}
