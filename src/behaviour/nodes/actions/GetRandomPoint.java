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
        //System.out.println("GET RANDOM");
    }

    @Override
    public void onStop() {
        //System.out.println(state);
        if (DEBUG) System.out.println("GetRandomPoint = " + state);
    }

    @Override
    public State onUpdate() {
        if (bb.randomTarget == null) {
            bb.randomTarget = getWalkablePoint();
        }
        else if (owner.position().equals(bb.randomTarget)) {
            bb.randomTarget = getWalkablePoint();
        }
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
