package entities.vegetals;

import behaviour.tree.BehaviourTree;
import entities.Entity;

/**
 * Vegetal version of an entity.
 */
public abstract class Vegetal extends Entity {
    public Vegetal(BehaviourTree behaviour) {
        super(behaviour);
    }

    @Override
    public void update() {
        behaviour.update(this);
    }
}
