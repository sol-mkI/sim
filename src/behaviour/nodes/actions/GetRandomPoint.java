package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import environment.Tile;
import pathfinding.Point2D;

import java.util.Random;

public class GetRandomPoint extends Leaf {
    private Random random;
    @Override
    public void onStart() {
        random = new Random(System.nanoTime());
    }

    @Override
    public void onStop() {
        System.out.println("GetRandomPoint = " + state);
    }

    @Override
    public State onUpdate() {
        bb.lastTarget = bb.target;
        bb.target = getWalkablePoint();
        return State.SUCCESS;
    }

    private Point2D getWalkablePoint() {
        Point2D size = owner.tile().grid().getSize();
        Point2D p = new Point2D();
        Tile tile;
        do {
            p.move(random.nextInt(size.x - 1), random.nextInt(size.y - 1));
            tile = owner.tile().grid().tile(p);
        } while(tile.isObstacle());
        return p;
    }
}
