package behaviour.nodes.actions;

import behaviour.nodes.Leaf;
import behaviour.tree.State;
import entities.Entity;
import entities.Species;

import java.util.Arrays;
import java.util.List;

/**
 * If there is an entity of a given species in the same tile, it dies and the owner entity gets a bonus.
 */
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
    public State onUpdate(Entity entity) {
        for (Entity target : em.retrieveEntities(entity.position())) {
            Species s = target.specie();
            if (toEat.contains(s)) {
                em.removeEntity(target);
                if(entity.checkAtt("health") < 50)
                    entity.incrementAtt("health", bb.foodValues.get(s));
                bb.target = null;
                return State.SUCCESS;
            }
        }
        return State.FAILURE;
    }
}
