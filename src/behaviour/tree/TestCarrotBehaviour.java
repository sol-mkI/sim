package behaviour.tree;

import behaviour.nodes.Nodes;
import behaviour.nodes.Node;
import entities.EntityManager;

/**
 * Test carrot behaviour
 */
public class TestCarrotBehaviour extends BehaviourTree {
    /**
     * Constructor
     */
    public TestCarrotBehaviour(EntityManager entityManager) {
        super(entityManager);
        root = buildTree();
        traverse(root);
    }
    /**
     * Build tree sequence.
     */
    private Node buildTree() {
        return Nodes.doCarrot(); // 0.0034 x 1 Rabbit = BALANCE
    }
}



























