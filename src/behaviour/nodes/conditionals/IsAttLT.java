package behaviour.nodes.conditionals;

import behaviour.nodes.Node;
import behaviour.tree.State;
import entities.Entity;

/**
 * Checks whether a given attribute is lesser than a given amount.
 */
public class IsAttLT extends Node {
    private final String att;
    private final int amount;

    public IsAttLT(String att, int amount) {
        this.att = att;
        this.amount = amount;
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {if (DEBUG) System.out.println(state);}

    @Override
    public State onUpdate(Entity entity) {
        if (entity.checkAtt(att) < amount)
            return State.SUCCESS;
        return State.FAILURE;
    }
}
