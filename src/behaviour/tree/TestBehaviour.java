package behaviour.tree;

import behaviour.nodes.actions.FollowPath;
import behaviour.nodes.actions.GetPath;
import behaviour.nodes.actions.GetRandomPoint;
import behaviour.nodes.actions.TargetSpecies;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.conditionals.IsPathValid;
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
        Sequence sequence = new Sequence();
        sequence.children.add(speciesInRange);
        sequence.children.add(targetSpecies);

        IsTargetValid isTargetValid = new IsTargetValid();
        GetRandomPoint getRandomPoint = new GetRandomPoint();
        Selector selector = new Selector();
        selector.children.add(isTargetValid);
        selector.children.add(getRandomPoint);

        Selector selector1 = new Selector();
        selector1.children.add(sequence);
        selector1.children.add(selector);

        IsPathValid isPathValid = new IsPathValid();
        GetPath getPath = new GetPath();
        Selector selector2 = new Selector();
        selector2.children.add(isPathValid);
        selector2.children.add(getPath);

        FollowPath followPath = new FollowPath();
        Sequence sequence1 = new Sequence();
        sequence1.children.add(selector2);
        sequence1.children.add(followPath);

        Sequence sequence2 = new Sequence();
        sequence2.children.add(selector1);
        sequence2.children.add(sequence1);

        Repeater repeater = new Repeater();
        repeater.child = sequence2;

        root = repeater;

        bind();
    }

    private void setupBlackboard() {
        bb.obstacles = new ArrayList<>();

        bb.food = new ArrayList<>();
        bb.food.add(Species.CARROT);

        bb.visionRange = 5;

    }
}
