package behaviour.tree;

import behaviour.nodes.Composite;
import behaviour.nodes.Decorator;
import behaviour.nodes.Node;
import behaviour.nodes.decorators.Root;
import entities.Entity;
import entities.EntityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Base behaviour tree.
 */
public abstract class BehaviourTree {

    /**
     * Root Node of the tree
     */
    protected Node root;
    /**
     * Current state of the tree
     */
    private State state;
    /**
     * Interface for the tree to operate on an entity.
     */
    private final EntityManager em;
    /**
     * Collection of values relative to an entity.
     */
    protected final Blackboard bb = new Blackboard();

    /**
     * Constructor
     */
    public BehaviourTree(EntityManager em) {
        this.em = em;
    }

    /**
     * Run a tick down the tree updating each node until success or failure.
     */
    public State update(Entity entity) {
        if (root.state == State.RUNNING)
            state = root.update(entity);
        return state;
    }

    /**
     * Returns a list with the children of a node.
     * @param parent node
     * @return children, empty list if no children
     */
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
        }

        return children;
    }

    /**
     * Traverse the tree in preorder to assign variables to each node.
     */
    public void traverse(Node node) {
        if (node != null) {
            node.bb = bb;
            node.em = em;
            for (Node child : getChildren(node)) {
                child.bb = bb;
                node.em = em;
                traverse(child);
            }
        }
    }
}
