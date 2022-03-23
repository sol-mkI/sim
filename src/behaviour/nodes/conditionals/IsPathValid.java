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
        if (DEBUG) System.out.println("Checking if path is valid");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println("IsPathValid = " + state);
    }

    @Override
    public State onUpdate() {
        if (bb.path == null) {
            if (DEBUG) System.out.println("Exit 0");
            return State.FAILURE;
        }
        if (bb.path.isEmpty()) {
            if (DEBUG) System.out.println("Exit 1");
            return State.FAILURE;
        }
        if (bb.target == null) {
            if (DEBUG) System.out.println("Exit 2");
            System.out.println("Can't check if path is valid without a target");
            return State.FAILURE;
        }
        if (!bb.path.get(bb.path.size() - 1).equals(bb.target)) {
            if (DEBUG) System.out.println("**********************************************************");
            if (DEBUG) System.out.println("1-> " + bb.path);
            if (DEBUG) System.out.println("2-> " + bb.path.size());
            if (DEBUG) System.out.println("3-> " + bb.target);
            if (DEBUG) System.out.println("Exit 3");
            if (DEBUG) System.out.println("**********************************************************");

            return State.FAILURE;
        }

        for (Point2D p : bb.path) {

            if (DEBUG_COLOR) owner.tile().grid().tile(p).setColor(Color.AQUAMARINE);

            for (Entity e : owner.tile().grid().tile(p).getEntities()) {
                if (bb.obstacles.stream().anyMatch(o -> e.specie().equals(o))) {
                    if (DEBUG) System.out.println("Exit 4");

                    return State.FAILURE;
                }
            }
        }
        if (DEBUG) System.out.println("Exit 5");

        return State.SUCCESS;
    }
}
