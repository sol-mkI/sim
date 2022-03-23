package behaviour.tree;

import behaviour.nodes.actions.*;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.conditionals.IsTargetReached;
import behaviour.nodes.conditionals.IsTargetValid;
import behaviour.nodes.actions.SpeciesInRange;
import behaviour.nodes.decorators.Repeater;
import entities.Entity;
import entities.Species;

import java.util.ArrayList;

public class TestBehaviour extends BehaviourTree {

    public TestBehaviour(Entity owner) {
        setupBlackboard();
        this.owner = owner;

        /*SpeciesInRange speciesInRange = new SpeciesInRange();
        TargetSpecies targetSpecies = new TargetSpecies();
        Sequence rangecheck = new Sequence();
        rangecheck.children.add(speciesInRange);
        rangecheck.children.add(targetSpecies);

        IsTargetValid isTargetValid = new IsTargetValid();
        GetRandomPoint getRandomPoint = new GetRandomPoint();
        Selector randomPoint = new Selector();
        randomPoint.children.add(isTargetValid);
        randomPoint.children.add(getRandomPoint);

        Selector gettingTarget = new Selector();
        gettingTarget.children.add(rangecheck);
        gettingTarget.children.add(randomPoint);

        IsTargetReached isTargetReached = new IsTargetReached();
        //IsPathValid isPathValid = new IsPathValid();
        GetPath getPath = new GetPath();
        FollowPath followPath = new FollowPath();
        EatSpecies eatSpecies = new EatSpecies();

        Sequence eatTarget = new Sequence();
        eatTarget.children.add(isTargetReached);
        eatTarget.children.add(eatSpecies);

        //Selector traversePath = new Selector();
        //traversePath.children.add(isPathValid);
        //traversePath.children.add(followPath);

        Sequence movement = new Sequence();
        movement.children.add(getPath);
        movement.children.add(followPath);

        Selector goToTargetAndEat = new Selector();
        goToTargetAndEat.children.add(eatTarget);
        goToTargetAndEat.children.add(movement);

        Sequence main = new Sequence();
        main.children.add(gettingTarget);
        main.children.add(goToTargetAndEat);*/

        Selector s1 = new Selector();
        SpeciesInRange speciesInRange =new SpeciesInRange();
        GetRandomPoint getRandomPoint = new GetRandomPoint();
        s1.children.add(speciesInRange);
        s1.children.add(getRandomPoint);

        GetPath getPath = new GetPath();
        FollowPath followPath = new FollowPath();
        EatSpecies eatSpecies = new EatSpecies();

        Sequence q1 = new Sequence();
        q1.children.add(s1);
        q1.children.add(getPath);
        q1.children.add(followPath);
        q1.children.add(eatSpecies);

        Repeater repeater = new Repeater();
        repeater.child = q1;

        root = repeater;

        bind();
    }

    private void setupBlackboard() {
        bb.obstacles = new ArrayList<>();

        bb.food = new ArrayList<>();
        bb.food.add(Species.CARROT);

        bb.visionRange = 20;

    }
}
