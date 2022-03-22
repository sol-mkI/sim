package behaviour;

import entities.Entity;

public class TestBehaviour {
    BehaviourTree tree;
    Entity owner;

    public TestBehaviour(Entity owner) {
        this.owner = owner;
        tree = new BehaviourTree();

        Log log0 = new Log();
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

        tree.root = repeater;
        tree.bind(owner);
    }

    public void update() {
        tree.update();
    }
}
