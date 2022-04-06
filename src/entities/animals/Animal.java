package entities.animals;

import behaviour.tree.BehaviourTree;
import entities.Detection;
import entities.Entity;
import entities.EntityManager;
import entities.Species;

import java.util.*;

/**
 * Animal version of an Entity.
 */
public abstract class Animal extends Entity {

    /**
     * Cache all entities within sight at the priority calculation step.
     */
    List<Detection> withinSight = new ArrayList<>();
    /**
     * Priority action values for interaction with species.
     */
    protected final Map<Species, Integer> priorityMap = new HashMap<>();

    public Animal(BehaviourTree behaviour) {
        super(behaviour);
    }

    @Override
    public void update() {
        behaviour.update(this);
    }

    @Override
    public void computePriority(List<Detection> detectionList) {
        priorityList.clear();
        for (Detection detection : withinSight = detectionList) {
            int priority = priorityMap.get(
                    detection.detected().specie())
                    /
                    detection.distance() != 0 ?
                    detection.distance() :
                    1;

            if (priorityList.size() == PRIORITY_SIZE) {
                int index = priorityList.size() - 1;
                if (priorityList.get(index) < priority)
                    priorityList.set(index, priority);
            } else {
                priorityList.add(priority);
            }
        }
    }
}
