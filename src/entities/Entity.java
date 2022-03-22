package entities;

import java.util.ArrayList;
import environment.Tile;
import pathfinding.Point2D;

import java.util.List;
import java.util.Random;

import static java.lang.Math.*;

public abstract class Entity implements Comparable<Entity> {

    protected static final boolean DEBUG = true;
    protected static final Random rand = new Random(System.nanoTime());

    // Base
    protected Tile tile;
    protected Species species;
    public final List<Integer> priorityList = new ArrayList<>();

    private final int maxHealth = Integer.MAX_VALUE;
    private int health = 20;


    public Entity(Tile tile) {
        this.tile = tile;
    }

    public void computePriority() {
        // Reset priorities every frame
        priorityList.clear();
        priorityList.add(0);
    }

    public abstract void update();

    public Species getSpecies() {
        return species;
    }

    @Override
    public int compareTo(Entity o) {
        for (int i = 0; i < min(priorityList.size(), o.priorityList.size()); i++) {
            int value = Double.compare(o.priorityList.get(i), priorityList.get(i));
            if (value != 0)
                return value;
        }
        return 0;
    }

    public void recieveDamage(int amount) {
        health -= amount;
        if (health <= 0)
            die();
    }

    private void die() {
        tile.removeEntity(this);
    }
    public Point2D position() {return tile.getLocation();}

    public Species specie() {
        return species;
    }
}
