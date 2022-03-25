package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;
import entities.Species;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

import java.util.*;


// Target first food species in range.
// If no food target is null.

public class SpeciesInRange extends Leaf {

    private Point2D pos;
    private final int range;
    private final int ITERATIONS;
    private final List<Species> toCheck;

    public SpeciesInRange(int range, Species... toCheck) {
        this.range = range;
        this.toCheck = Arrays.asList(toCheck);
        ITERATIONS = range * (-4 + 4*range);
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
        pos = owner.position();
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println(state);
    }

    @Override
    public State onUpdate() {
        List<Point2D> entityList = spiralIteration();

        if (entityList.isEmpty())
            return State.FAILURE;

        bb.target = entityList.get(0);
        return State.SUCCESS;
    }

    private List<Point2D> spiralIteration() {

        List<Point2D> pointList = new LinkedList<>();

        int deltaX = 1, deltaY = 0;
        int segmentLength = 1;

        int x = pos.x, y = pos.y;
        int segmentPassed = 0;

        for (int k = 0; ITERATIONS > k; ++k) {
            x += deltaX;
            y += deltaY;
            ++segmentPassed;

            if (checkPoint(pointList, x, y))
                break;

            if (segmentPassed == segmentLength) {
                segmentPassed = 0;

                int aux = deltaX;
                deltaX = -deltaY;
                deltaY = aux;

                if (deltaY == 0)
                    ++segmentLength;
            }
        }
        return pointList;
    }

    public int distance(int x1, int x2, int y1, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);
        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

    // Only returns the first target
    private boolean checkPoint(List<Point2D> pointList, int x, int y) {


        if (!owner.tile().grid().isCoordValid(x,y))
            return false;

        int distance = distance(pos.x, x, pos.y, y);
        if (distance > range)
            return false;

        if (DEBUG_COLOR) owner.tile().grid().tile(new Point2D(x,y)).setColor(Color.DARKKHAKI);

        for (Entity entity : owner.tile().grid().tile(x,y).getEntities())
            if (toCheck.contains(entity.specie())) {
                pointList.add(new Point2D(x,y));
                return true;
            }

        return false;
    }
}
