package entities;

import behaviour.tree.BehaviourTree;
import environment.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Base abstract class for any entity.
 * */
public abstract class Entity implements Comparable<Entity> {

    protected static final boolean DEBUG = true;
    /**
     * Species of the entity.
     */
    protected Species species;
    /**
     * Current position of the entity.
     */
    protected Point2D position;
    /**
     * Max priority array size, increase to minimize collisions, performance drops.
     */
    protected static final int PRIORITY_SIZE = 5;
    /**
     * Priority list used to compare entity action priority.
     */
    protected final List<Integer> priorityList = new ArrayList<>();
    /**
     * Map with attributes.
     */
    protected final Map<String, Integer> attributes = new HashMap<>();
    /**
     * Size of an entity.
     */
    protected int size;
    /**
     * Interaction range of an entity.
     */
    protected int range;
    /**
     * Set behaviour of an entity.
     */
    protected BehaviourTree behaviour;

    public Entity(BehaviourTree behaviour) {
        this.behaviour = behaviour;
    }
    /**
     * Abstract method, fills the priority array.
     */
    public abstract void computePriority(List<Detection> detectionList);
    /**
     * Abstract method, updates the entity to its next state.
     */
    public abstract void update();
    /**
     * Setter for the position of an entity.
     */
    public void setPosition(Point2D p) {
        this.position = p.getLocation();
    }
    /**
     * Getter for the position.
     */
    public Point2D position() {return position.getLocation();}
    /**
     * Getter for the species.
     */
    public Species specie() {return species;}
    /**
     * Getter for the size.
     */
    public int size() {return size;}
    public int visionRange() {return range;}
    /**
     * Getter for an attribute with a string.
     * @param att att
     */
    public int checkAtt(String att) {
        return attributes.get(att);
    }
    /**
     * Increases the amount of an attribute.
     */
    public void incrementAtt(String att, int amount) {
        attributes.merge(att, amount, Integer::sum);
    }

    /**
     * Decreases the amount of an attribute.
     */
    public void decreaseAtt(String att, int amount) {
        attributes.merge(att, -amount, Integer::sum);
    }

    @Override
    public String toString() {return getClass().getSimpleName();}
    @Override
    public int compareTo(Entity o) {
        int size = Math.min(priorityList.size(), o.priorityList.size());
        for (int i = 0; i < size; i++) {
            int value = Integer.compare(
                    o.priorityList.get(i),
                      priorityList.get(i));

            if (value != 0)
                return value;
        }
        return Integer.compare(o.priorityList.size(), priorityList.size());
    }
}