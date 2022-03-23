package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;
import entities.Species;
import environment.Tile;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pathfinding.Point2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


// Target first food species in range.
// If no food target is null.

public class SpeciesInRange extends Leaf {
    @Override
    public void onStart() {
        //System.out.println("Checking for species in Range");
    }

    @Override
    public void onStop() {
        //System.out.println(state);
        if (DEBUG) System.out.println("SpeciesInRange = " + state);
    }

    @Override
    public State onUpdate() {
        List<Pair<Point2D, Integer>> entityList = spiralCheck();

        if (entityList.isEmpty()) {
            bb.target = null;
            return State.FAILURE;
        }
        bb.target = entityList.get(0).getKey();
        return State.SUCCESS;

    }

    public int distance(int x1, int x2, int y1, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);
        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

    private List<Pair<Point2D, Integer>> spiralCheck() {
        List<Pair<Point2D, Integer>> points = new ArrayList<>();

        Point2D o = owner.position();

        for (int i = o.x - bb.visionRange; i < o.x + bb.visionRange; i++)
            for (int j = o.y - bb.visionRange; j < o.y + bb.visionRange; j++) {

                int distance = distance(o.x, i, o.y, j);
                if (distance > bb.visionRange)
                    continue;

                if (!owner.tile().grid().isCoordValid(i,j))
                    continue;

                for (Entity entity : owner.tile().grid().tile(i,j).getEntities())
                    if (bb.food.contains(entity.getSpecies()))
                        points.add(new Pair<>(new Point2D(i,j), distance));
            }

        points.sort(Comparator.comparing(Pair::getValue));
        return points;
    }

    /*private List<Entity> checkArea() {
        List<Entity> entityList = new ArrayList<>();
        Point2D origin = owner.position();
        for (int i = origin.x - bb.visionRange; i < origin.x + bb.visionRange; i++)
            for (int j = origin.y - bb.visionRange; j < origin.y + bb.visionRange; j++) {
                Point2D target = new Point2D(i, j);
                checkPoint(bb.food, entityList, origin, target);
            }
        return entityList;
    }
    private void checkPoint(List<Species> species, List<Entity> entityList, Point2D origin, Point2D target) {
        if (owner.tile().grid().isCoordValid(target))
            if (origin.distance(target) < bb.visionRange) {
                Tile tile = owner.tile().grid().tile(target);
                //if(DEBUG_COLOR) tile.setColor(Color.DARKKHAKI);
                for (Entity entity : tile.getEntities())
                    if (species.contains(entity.getSpecies()))
                        entityList.add(entity);
            }
    }*/
}