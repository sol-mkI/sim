package behaviour.nodes;

import behaviour.nodes.actions.*;
import behaviour.nodes.base.Node;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.decorators.Repeater;

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

    public static Node foodInRange() {
        return new SpeciesInRange();
    }

    public static Node randomPoint() {
        return new GetRandomPoint();
    }

    public static Node getPath() {
        return new GetPath();
    }

    public static Node followPath() {
        return new FollowPath();
    }

    public static Node eat() {
        return new EatSpecies();
    }
}
