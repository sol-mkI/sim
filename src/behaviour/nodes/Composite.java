package behaviour.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Composite Node, can have multiple children, minimum one.
 */
public abstract class Composite extends Node {
    public List<Node> children = new ArrayList<>();
}
