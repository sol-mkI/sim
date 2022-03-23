package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;
import pathfinding.Point2D;

public class GetPath extends Leaf {
    @Override
    public void onStart() {
        if (DEBUG) System.out.println("Path Requested | " + owner.position() + " | " + bb.target);
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println("GetPath = " + state);
    }

    @Override
    public State onUpdate() {
        if (DEBUG) System.out.println("Moving " + bb.target + " <-> " + bb.lastTarget);
        if (bb.target.equals(bb.lastTarget)) {
            if (bb.path == null) {
                bb.path = owner.tile().grid().requestPath(owner.position(), bb.target);
                return bb.path != null ? State.SUCCESS : State.FAILURE;
            } else {
                if (DEBUG) System.out.println("Returning Old Path");
                return State.SUCCESS;
            }
            /*for (Point2D p : bb.path) {
                for (Entity e : owner.tile().grid().tile(p).getEntities()) {
                    if (bb.obstacles.stream().anyMatch(o -> e.specie().equals(o))) {
                        if (DEBUG) System.out.println("New Path Requested0");
                        bb.path = owner.tile().grid().requestPath(owner.position(), bb.target);
                        return bb.path != null ? State.SUCCESS : State.FAILURE;
                    }
                }
            }*/

        }

        bb.path = owner.tile().grid().requestPath(owner.position(), bb.target);
        if (DEBUG) System.out.println("New Path Requested1");
        return bb.path != null ? State.SUCCESS : State.FAILURE;
    }
}
