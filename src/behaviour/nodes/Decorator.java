package behaviour.nodes;

/**
 * Base Decorator node, can only have and has to have one child.
 */
public abstract class Decorator extends Node {
    public Node child;
}
