package behaviour.nodes.conditionals;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;
import entities.Species;

/**
 * Checks whether there is a valid target.
 */
public class IsTargetValid extends Leaf {
    private final Species targetSpecie;
    public IsTargetValid(Species specie) {
        this.targetSpecie = specie;
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println(state);
    }

    @Override
    public State onUpdate(Entity entity) {
        if (bb.target == null || !bb.target.specie().equals(targetSpecie))
            return State.FAILURE;
        return State.SUCCESS;
    }
}
