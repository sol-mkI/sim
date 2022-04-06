package behaviour.nodes.actions;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;

/**
 * Decreases a given attribute by a given amount.
 */
public class DecreaseAtt extends Leaf {

    private final int amount;
    private final String att;

    public DecreaseAtt(String att, int amount) {
        this.amount = amount;
        this.att = att;
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {if (DEBUG) System.out.println(state);}

    @Override
    public State onUpdate(Entity entity) {
        entity.decreaseAtt(att, amount);
        return State.SUCCESS;
    }
}
