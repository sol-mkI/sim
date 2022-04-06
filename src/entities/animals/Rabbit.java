package entities.animals;

import behaviour.tree.BehaviourTree;
import behaviour.tree.TestRabbitBehaviour;
import entities.EntityManager;
import entities.Species;

/**
 * Ribbit
 */
public class Rabbit extends Animal {
    /**
     * Constructor
     */
    public Rabbit(BehaviourTree behaviour) {
        super(behaviour);

        priorityMap.put(Species.CARROT, 10);
        priorityMap.put(Species.RABBIT, 10);

        species = Species.RABBIT;
        size = 1;
        attributes.put("health", 100);
    }
}
