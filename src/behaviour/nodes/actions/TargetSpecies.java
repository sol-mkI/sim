package behaviour.nodes.actions;

import behaviour.tree.State;
import behaviour.nodes.base.Leaf;
import entities.Entity;
import entities.Species;
import environment.Tile;
import javafx.scene.paint.Color;
import pathfinding.Point2D;

import java.util.ArrayList;
import java.util.List;

public class TargetSpecies extends Leaf {
    @Override
    public void onStart() {
        System.out.println("Targeting nearest species.");

    }

    @Override
    public void onStop() {
        System.out.println("TargetSpecies = " + state);

    }

    @Override
    public State onUpdate() {
        List<Entity> entityList = checkArea();

        if (entityList.isEmpty()) return State.FAILURE;
        else {
            bb.lastTarget = bb.target;
            bb.target = entityList.get(0).position();  // if need to process multiple targets, return entire list?
            return State.SUCCESS;
        }
    }

    private List<Entity> checkArea() {
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
                tile.setColor(Color.DARKKHAKI);
                for (Entity entity : tile.getEntities())
                    if (species.contains(entity.getSpecies()))
                        entityList.add(entity);
            }
    }
}
