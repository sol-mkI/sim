package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;
import pathfinding.Point2D;

public class GetPath extends Leaf {
    @Override
    public void onStart() {
        System.out.println("Path Requested | " + owner.position() + " | " + bb.target);
    }

    @Override
    public void onStop() {
        System.out.println("GetPath = " + state);
    }

    @Override
    public State onUpdate() {
        System.out.println(bb.target + " <-> " + bb.lastTarget);
        if (bb.target.equals(bb.lastTarget)) {
            for (Point2D p : bb.path) {
                for (Entity e : owner.tile().grid().tile(p).getEntities()) {
                    if (bb.obstacles.stream().anyMatch(o -> e.specie().equals(o))) {
                        System.out.println("New Path Requested0");
                        bb.path = owner.tile().grid().requestPath(owner.position(), bb.target);
                        return bb.path != null ? State.SUCCESS : State.FAILURE;
                    }
                }
            }
            System.out.println("Returning Old Path");
            return State.SUCCESS;
        }

        bb.path = owner.tile().grid().requestPath(owner.position(), bb.target);
        System.out.println("New Path Requested1");
        return bb.path != null ? State.SUCCESS : State.FAILURE;
    }
}
