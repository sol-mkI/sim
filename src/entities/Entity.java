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
    protected int health = 20;
    protected int size;

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


    public void recieveDamage(int amount) {
        health -= amount;
        if (health <= 0)
            die();
    }

    protected void die() {
        tile.removeEntity(this);
    }
    public Point2D position() {return tile.location();}
    public Species specie() {
        return species;
    }
    public Tile tile() {return tile;}

    public void move(Tile tile) {
        tryMove(tile);
        this.tile = tile;
    }

    private void tryMove(Tile tile) {
    }

    private int eaten = 0;
    public void eat(Entity target) {
        //TODO: Add benefit to entity eating.
        target.die();
        eaten++;
    }
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + eaten;
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
}
