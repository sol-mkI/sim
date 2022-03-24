package behaviour.tree;

import behaviour.nodes.Nodes;
import entities.Entity;
import entities.Species;

import java.util.ArrayList;

public class TestBehaviour extends BehaviourTree {

    public TestBehaviour(Entity owner) {
        setupBlackboard();
        this.owner = owner;
        build();
        bind();
    }

    private void setupBlackboard() {
        bb.obstacles = new ArrayList<>();

        bb.food = new ArrayList<>();
        bb.food.add(Species.CARROT);

        bb.visionRange = 10;

    }

    private void build() {
        root = Nodes.repeater(
            Nodes.sequence(
                Nodes.selector(
                    Nodes.foodInRange(),
                    Nodes.randomPoint()
                ),
                Nodes.getPath(),
                Nodes.followPath(),
                Nodes.eat()
            )
        );
    }
}



























