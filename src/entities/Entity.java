package entities;

import environment.Tile;
import pathfinding.Point2D;

import java.util.PriorityQueue;
import java.util.Random;

public abstract class Entity implements Comparable<Entity> {

    protected static final boolean DEBUG = true;
    protected static final Random rand = new Random(System.nanoTime());

    // Base
    protected Tile tile;
    protected Species species;

    // Prioritat
    protected static final int PRIORITY_SIZE = 5;
    protected final int[] priority = new int[PRIORITY_SIZE];
    protected final PriorityQueue<Integer> subPQ = new PriorityQueue<>();

    // Atributs
    private final int maxHealth = Integer.MAX_VALUE;
    protected int health;
    protected int size;

    public Entity(Tile tile) {
        this.tile = tile;
    }

    public abstract void computePriority();
    public abstract void update();

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
        this.tile = tile;
    }

    public boolean eat(Entity target) {
        target.die();
        return true;
    }
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public int compareTo(Entity o) {
        for (int i = 0; i < PRIORITY_SIZE; i++) {
            int value = Integer.compare(o.priority[i], priority[i]);
            if (value != 0)
                return value;
        }
        return 0;
    }
}
