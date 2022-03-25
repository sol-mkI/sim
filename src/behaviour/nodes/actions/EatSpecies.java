package behaviour.nodes.actions;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;
import entities.Species;

import java.util.Arrays;
import java.util.List;

public class EatSpecies extends Leaf {

    private final List<Species> toEat;

    public EatSpecies(Species... species) {
        toEat = Arrays.asList(species);
    }

    @Override
    public void onStart() {
        if (DEBUG) System.out.print(getClass().getSimpleName() + " ");
    }

    @Override
    public void onStop() {
        if (DEBUG) System.out.print(state);
    }

    @Override
    public State onUpdate() {
        for (Entity entity : owner.tile().getEntities())
            for (Species species : toEat)
                if (entity.specie().equals(species)) {
                    bb.target = null;
                    return owner.eat(entity) ? State.SUCCESS : State.FAILURE;
                }

        return State.FAILURE;
    }
}
