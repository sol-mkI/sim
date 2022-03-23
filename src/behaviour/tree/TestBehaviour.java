package behaviour.tree;

import behaviour.nodes.actions.*;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.conditionals.IsPathValid;
import behaviour.nodes.conditionals.IsTargetReached;
import behaviour.nodes.conditionals.IsTargetValid;
import behaviour.nodes.conditionals.SpeciesInRange;
import behaviour.nodes.decorators.Repeater;
import entities.Entity;
import entities.Species;

import java.util.ArrayList;

public class TestBehaviour extends BehaviourTree {

    public TestBehaviour(Entity owner) {
        setupBlackboard();
        this.owner = owner;

        /*Log log0 = new Log();
        log0.message = "0";
        Wait wait0 = new Wait();
        wait0.duration = 1;
        Log log1 = new Log();
        log1.message = "1";
        Wait wait1 = new Wait();
        wait1.duration = 1;
        Log log2 = new Log();
        log2.message = "2";
        Wait wait2 = new Wait();
        wait2.duration = 1;

        Sequence sequence = new Sequence();
        sequence.children.add(log0);
        sequence.children.add(wait0);
        sequence.children.add(log1);
        sequence.children.add(wait1);
        sequence.children.add(log2);
        sequence.children.add(wait2);

        Repeater repeater = new Repeater();
        repeater.child = sequence;

        root = repeater;*/

        /*// if species in range, go to species
        // if not, go to random point
        Selector selector = new Selector();
        SpeciesInRange speciesInRange = new SpeciesInRange();
        GetRandomPoint getRandomPoint = new GetRandomPoint();
        selector.children.add(speciesInRange);
        selector.children.add(getRandomPoint);

        Sequence sequence = new Sequence();
        GetPath getPath = new GetPath();
        //IsPathValid isPathValid = new IsPathValid();
        FollowPath followPath = new FollowPath();
        sequence.children.add(getPath);
        //sequence.children.add(isPathValid);
        sequence.children.add(followPath);

        Sequence sequence1 = new Sequence();
        sequence1.children.add(selector);
        sequence1.children.add(sequence);

        Repeater repeater = new Repeater();
        repeater.child = sequence1;

        root = repeater;*/



        SpeciesInRange speciesInRange = new SpeciesInRange();
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
        IsPathValid isPathValid = new IsPathValid();
        GetPath getPath = new GetPath();
        FollowPath followPath = new FollowPath();
        EatSpecies eatSpecies = new EatSpecies();

        Sequence eatTarget = new Sequence();
        eatTarget.children.add(isTargetReached);
        eatTarget.children.add(eatSpecies);

        Selector gettingPath = new Selector();
        gettingPath.children.add(isPathValid);
        gettingPath.children.add(getPath);

        Sequence followingPath = new Sequence();
        followingPath.children.add(gettingPath);
        followingPath.children.add(followPath);

        Selector goToTargetAndEat = new Selector();
        goToTargetAndEat.children.add(eatTarget);
        goToTargetAndEat.children.add(followingPath);

        /*selector3.children.add(isTargetReached);
        selector3.children.add(sequence1);
        Sequence sequence2 = new Sequence();
        sequence2.children.add(selector3);
        sequence2.children.add(eatSpecies);*/

        Sequence main = new Sequence();
        main.children.add(gettingTarget);
        main.children.add(goToTargetAndEat);

        Repeater repeater = new Repeater();
        repeater.child = main;

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
