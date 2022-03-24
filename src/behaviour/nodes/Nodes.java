package behaviour.nodes;

import behaviour.nodes.actions.*;
import behaviour.nodes.base.Node;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.conditionals.IsTargetValid;
import behaviour.nodes.conditionals.OnTarget;
import behaviour.nodes.decorators.Inverter;
import behaviour.nodes.decorators.Repeater;
import entities.Species;

import java.util.Arrays;

public class Nodes {

    public static Node repeater(Node child) {
        Repeater repeater = new Repeater();
        repeater.child = child;
        return repeater;
    }

    public static Node sequence(Node... child) {
        Sequence sequence = new Sequence();
        sequence.children.addAll(Arrays.asList(child));
        return sequence;
    }
    public static Node selector(Node... child) {
        Selector selector = new Selector();
        selector.children.addAll(Arrays.asList(child));
        return selector;
    }

    public static Node getFirstSpeciesInRange(int range, Species... species) {
        return new SpeciesInRange(range, species);
    }

    public static Node foodInRange(int range, Species... food) {
        return new SpeciesInRange(range, food);
    }
    public static Node randomPoint(int range) {
        return new GetRandomPoint(range);
    }
    public static Node getPath() {
        return new GetPath();
    }
    public static Node followPath() {
        return new FollowPath();
    }
    public static Node eat(Species... species) {
        return new EatSpecies(species);
    }

    /*public static Node computePoint(String escape) {
        return null;
    }*/

    /*public static Node isTargetReached() {
        return null;
    }*/

    /*public static Node reproduce() {
        return null;
    }*/

    public static Node getPathAndFollow() {
        return Nodes.sequence(
                Nodes.getPath(),
                Nodes.followPath()
        );
    }

    public static Node isTargetValid() {
        return new IsTargetValid();
    }
    public static Node onTarget() {
        return new OnTarget();
    }
    public static Node inverter(Node child) {
        Inverter inverter = new Inverter();
        inverter.child =child;
        return inverter;
    }
}
