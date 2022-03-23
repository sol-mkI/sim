package behaviour.nodes.conditionals;
import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

// Checks if there is an obstacle (non-traversable species) in the path.

public class IsPathValid extends Leaf {
    @Override
    public void onStart() {
        System.out.println("Checking if path is valid");
    }

    @Override
    public void onStop() {
        System.out.println("IsPathValid = " + state);
    }

    @Override
    public State onUpdate() {
        if (bb.path == null) return State.FAILURE;
        if (!bb.path.get(bb.path.size() - 1).equals(bb.target)) return State.FAILURE;

        for (Point2D p : bb.path) {

            if (DEBUG) owner.tile().grid().tile(p).setColor(Color.AQUAMARINE);

            for (Entity e : owner.tile().grid().tile(p).getEntities()) {
                if (bb.obstacles.stream().anyMatch(o -> e.specie().equals(o))) {
                    return State.FAILURE;
                }
            }
        }
        return State.SUCCESS;
    }
}