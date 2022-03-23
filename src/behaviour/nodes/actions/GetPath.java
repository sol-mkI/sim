package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import javafx.scene.paint.Color;
import org.omg.PortableInterceptor.SUCCESSFUL;
import pathfinding.Point2D;

import java.util.List;

public class GetPath extends Leaf {
    @Override
    public void onStart() {
        //System.out.println("Get Path");
    }

    @Override
    public void onStop() {
        //System.out.println(state);

        if (DEBUG) System.out.println("GetPath = " + state);
    }

    @Override
    public State onUpdate() {
        if (bb.target != null) {
            if (bb.path != null && !bb.path.isEmpty())
                if (bb.target.equals(bb.path.get(bb.path.size() - 1)))
                    return State.SUCCESS;
            requestPath(bb.target);
            return bb.path != null ? State.SUCCESS : State.FAILURE;
        }

        if (bb.randomTarget != null) {
            if (bb.path != null && !bb.path.isEmpty())
                if (bb.randomTarget.equals(bb.path.get(bb.path.size() - 1)))
                    return State.SUCCESS;
            requestPath(bb.randomTarget);
            return bb.path != null ? State.SUCCESS : State.FAILURE;
        }

        return State.FAILURE;
    }

    private void requestPath(Point2D p) {
        bb.path = owner.tile().grid().requestPath(owner, owner.position(), p);

        if (DEBUG_COLOR)
            for (Point2D q : bb.path)
                owner.tile().grid().tile(q).setColor(Color.AQUAMARINE);
    }

}
