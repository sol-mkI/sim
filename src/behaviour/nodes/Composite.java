package behaviour.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends Node {
    public List<Node> children = new ArrayList<>();
}