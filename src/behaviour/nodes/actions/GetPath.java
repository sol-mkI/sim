package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

public class GetPath extends Leaf {

    private Point2D target;

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
        this.target = bb.target;
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.print(state);
        target = null;
    }

    @Override
    public State onUpdate() {
        if (target == null) return State.FAILURE;

        if (bb.path != null &&
            !bb.path.isEmpty() &&
            target.equals(bb.path.get(bb.path.size() - 1)))
            return State.SUCCESS;

        requestPath(target);
        return bb.path != null ? State.SUCCESS : State.FAILURE;

    }

    private void requestPath(Point2D p) {
        bb.path = owner.tile().grid().requestPath(owner, owner.position(), p);

        if (DEBUG_COLOR)
            for (Point2D q : bb.path)
                owner.tile().grid().tile(q).setColor(Color.AQUAMARINE);
    }

}
