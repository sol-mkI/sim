package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import environment.Tile;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

import java.util.Random;

public class GetRandomPoint extends Leaf {

    private Random random;
    private final int range;
    private Point2D pos;

    public GetRandomPoint(int range) {
        this.range = range;
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
        random = new Random(System.nanoTime());
        pos = owner.position();
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.print(state);
        pos = null;
    }

    @Override
    public State onUpdate() {
        bb.target = getWalkablePoint();
        if (bb.target.equals(new Point2D()))
            return State.FAILURE;
        return State.SUCCESS;
    }

    private Point2D getWalkablePoint() {
        Point2D size = owner.tile().grid().getSize();
        Point2D p = new Point2D();
        Tile tile;

        int xLow = Math.max(pos.x - range, 0);
        int xHigh = Math.min(pos.x + range, size.x-1);
        int yLow = Math.max(pos.y - range, 0);
        int yHigh = Math.min(pos.y + range, size.y-1);

        //TODO: FIX BOUNDS
        int xBound = random.nextInt(xHigh - xLow) + xLow;
        int yBound = random.nextInt(yHigh - yLow) + yLow;

        do {
            p.move(random.nextInt(size.x-1), random.nextInt(size.y - 1));
            tile = owner.tile().grid().tile(p);
        } while(tile.isObstacle() || !tile.canFit(owner));
        owner.tile().grid().tile(p).setColor(Color.RED);
        return p;
    }
}
