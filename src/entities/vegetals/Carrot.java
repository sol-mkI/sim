package entities.vegetals;

import entities.Species;
import environment.Tile;
import utils.Utils;

public class Carrot extends Vegetal{
    public Carrot(Tile tile) {
        super(tile);
        health = 40;
        species = Species.CARROT;
        size = 0;
    }

    @Override
    void tryReproduce(boolean b) {
        if (b) {
            Tile targetTile = Utils.random(tile.grid().getNeighbours(tile));
            if (!targetTile.isObstacle() &&
                targetTile.getEntities().stream().noneMatch(e -> e instanceof Vegetal)) {

                Carrot carrot = new Carrot(targetTile);
                targetTile.addEntity(carrot);
            }
        }
    }

    @Override
    public void computePriority() {}

    @Override
    public void update() {
        recieveDamage(1);
        tryReproduce(rand.nextDouble() < 0.05);
    }
}
