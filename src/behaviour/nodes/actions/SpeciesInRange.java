package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.Leaf;
import entities.Entity;
import entities.Species;
import environment.Point2D;
import utils.Utils;

import java.util.*;


// Target first food species in range.
// If no food target is null.

/**
 * Checks for entities of some given Species in a given range and targets the first found.
 */
public class SpeciesInRange extends Leaf {

    private Point2D pos;
    private Point2D origin;
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

    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.println(state);
    }

    @Override
    public State onUpdate(Entity entity) {
        pos = entity.position();
        origin = entity.position();

        List<Point2D> points = spiralIteration();

        if (points.isEmpty())
            return State.FAILURE;

        for (Entity e : em.retrieveEntities(points.get(0))) {
            if (toCheck.contains(e.specie()))
                bb.target = e;
        }

        return State.SUCCESS;
    }

    private List<Point2D> spiralIteration() {

        List<Point2D> pointList = new LinkedList<>();

        int deltaX = 1, deltaY = 0;
        int segmentLength = 1;

        //int x = pos.x, y = pos.y;
        int segmentPassed = 0;

        for (int k = 0; ITERATIONS > k; ++k) {
            //pos.x += deltaX;
            //pos.y += deltaY;
            pos.add(deltaX, deltaY);
            ++segmentPassed;

            if (checkPoint(pointList))
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

    // Only returns the first target
    private boolean checkPoint(List<Point2D> pointList) {

        if (!em.isPositionValid(pos) || Utils.distance(origin, pos) > range)
            return false;

        for (Entity entity : em.retrieveEntities(pos))
            if (toCheck.contains(entity.specie()))
                return pointList.add(pos);

        return false;
    }
}
