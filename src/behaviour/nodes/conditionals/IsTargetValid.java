package behaviour.nodes.conditionals;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;

public class IsTargetValid extends Leaf {
    @Override
    public void onStart() {
        if (DEBUG) System.out.println("Checking if Target is Valid");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println("Target is " + state);
    }

    @Override
    public State onUpdate() {
        if (bb.target != null) {
            if (!bb.target.equals(owner.position())) return State.SUCCESS;
            for (Entity e : owner.tile().grid().tile(bb.target).getEntities())
                if (bb.food.stream().anyMatch(o -> e.specie().equals(o)))
                    return State.SUCCESS;
        }

        return State.FAILURE;
    }
}
