package entities.vegetals;

import entities.Entity;
import environment.Tile;

public abstract class Vegetal extends Entity {
    public Vegetal(Tile tile) {
        super(tile);
    }

    abstract void tryReproduce(boolean b);
}
