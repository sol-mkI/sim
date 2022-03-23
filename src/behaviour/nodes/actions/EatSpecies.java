package behaviour.nodes.actions;

import behaviour.nodes.base.Leaf;
import behaviour.tree.State;
import entities.Entity;
import entities.Species;

public class EatSpecies extends Leaf {
    @Override
    public void onStart() {
        //System.out.println("EAT");
    }

    @Override
    public void onStop() {
        //System.out.println(state);

    }

    @Override
    public State onUpdate() {
        for (Entity entity : owner.tile().getEntities()) {
            for (Species species : bb.food) {
                if (entity.specie().equals(species)) {
                    owner.eat(entity);
                    return State.SUCCESS;
                }
            }
        }
        return State.FAILURE;
    }
}
