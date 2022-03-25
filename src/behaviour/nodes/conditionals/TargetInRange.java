package behaviour.nodes.conditionals;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

public class TargetInRange extends Leaf {

    private final int range;
    private final int ITERATIONS;

    public TargetInRange(int range) {
        this.range = range;
        ITERATIONS = range * (-4 + 4*range);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        return spiralIteration() ? State.SUCCESS : State.FAILURE;
    }

    private boolean spiralIteration() {
        int deltaX = 1, deltaY = 0;
        int segmentLength = 1;

        int x = owner.position().x, y = owner.position().y;
        int segmentPassed = 0;

        for (int k = 0; ITERATIONS > k; ++k) {
            x += deltaX;
            y += deltaY;
            ++segmentPassed;

            if (checkPoint(x, y))
                return true;

            if (segmentPassed == segmentLength) {
                segmentPassed = 0;

                int aux = deltaX;
                deltaX = -deltaY;
                deltaY = aux;

                if (deltaY == 0)
                    ++segmentLength;
            }
        }
        return false;
    }

    private boolean checkPoint(int x, int y) {

        if (!owner.tile().grid().isCoordValid(x,y))
            return false;

        int distance = distance(owner.position().x, x, owner.position().y, y);
        if (distance > range)
            return false;

        if (DEBUG_COLOR) owner.tile().grid().tile(new Point2D(x,y)).setColor(Color.DARKKHAKI);

        for (Entity entity : owner.tile().grid().tile(x,y).getEntities())
            if (owner.specie().equals(entity.specie()))
                return true;

        return false;
    }

    public int distance(int x1, int x2, int y1, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);
        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }
}
