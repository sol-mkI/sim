package behaviour.nodes.actions;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;

/**
 * Removes the entity from the simulation.
 */
public class Die extends Leaf {
    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {if (DEBUG) System.out.println(state);}

    @Override
    public State onUpdate(Entity entity) {
        em.removeEntity(entity);
        return State.SUCCESS;
    }
}
