package behaviour.nodes.actions;

import behaviour.nodes.base.Leaf;
import behaviour.tree.State;
import entities.Entity;

public class EatSpecies extends Leaf {
    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public State onUpdate() {
        Entity target = null;
        // Get first entity with species in bb.food
        for (Entity entity : owner.tile().getEntities())
            if (bb.food.stream().anyMatch(e -> e.equals(entity.specie()))) {
                target = entity;
                break;
            }

        if (target == null) {
            System.out.println("Can't eat if there is no target");
            return State.FAILURE;
        }
        owner.eat(target);
        System.out.println("Entity " + owner + " has eaten " + target);
        return State.SUCCESS;
    }
}
