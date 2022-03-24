package behaviour.tree;

import behaviour.nodes.base.Composite;
import behaviour.nodes.base.Decorator;
import behaviour.nodes.base.Node;
import behaviour.nodes.decorators.Root;
import entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class BehaviourTree {
    public Node root;
    public State state;
    public Entity owner;
    public Blackboard bb = new Blackboard();


    public State update() {
        if (root.state == State.RUNNING) {
            state = root.update();
        }
        return state;
    }

    public List<Node> getChildren(Node parent) {
        List<Node>  children = new ArrayList<>();
        if (parent instanceof Decorator) {
            Decorator decorator = (Decorator) parent;
            if (decorator.child != null)
                children.add(decorator.child);
        }

        if (parent instanceof Root) {
            Root root = (Root) parent;
            if (root.child != null)
                children.add(root.child);
        }

        if (parent instanceof Composite) {
            Composite composite = (Composite) parent;
            return composite.children;
            /*if (composite.children != null)
                children.add(composite.child);*/
        }
        return children;
    }

    public void bind() {
        traverse(root);
    }

    private void traverse(Node node) {
        if (node != null) {
            node.bb = bb;
            node.owner = owner;
            for (Node child : getChildren(node)) {
                child.bb = bb;
                child.owner = owner;
                traverse(child);
            }
        }
    }
}
