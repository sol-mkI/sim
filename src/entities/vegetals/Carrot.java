package entities.vegetals;

import behaviour.tree.BehaviourTree;
import behaviour.tree.TestCarrotBehaviour;
import entities.Detection;
import entities.Species;
import entities.EntityManager;

import java.util.List;


/**
 * Carrot
 */
public class Carrot extends Vegetal{
    /**
     * Constructor
     */
    public Carrot(BehaviourTree behaviour) {
        super(behaviour);

        attributes.put("health", 40);
        attributes.put("size", 0);

        species = Species.CARROT;
    }

    @Override
    public void computePriority(List<Detection> detectionList) {
        priorityList.clear();
        priorityList.add(0);
    }
}
