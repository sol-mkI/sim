package behaviour.nodes.decorators;

import behaviour.nodes.Decorator;
import behaviour.tree.State;
import entities.Entity;

import java.util.Random;

/**
 * Random chance of executing the child node.
 */
public class RandomChance extends Decorator {

    private final double chance;
    private boolean execute = false;

    public RandomChance(double chance) {
        this.chance = chance;
    }

    @Override
    public void onStart() {
        Random random = new Random(System.nanoTime());
        execute = random.nextDouble() < chance;
    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate(Entity entity) {
        if (execute)
            return child.update(entity);
        return State.SUCCESS;
    }
}
