package behaviour.tree;

import behaviour.nodes.Nodes;
import behaviour.nodes.base.Node;
import entities.Entity;
import entities.Species;

public class TestBehaviour extends BehaviourTree {

    public TestBehaviour(Entity owner) {
        this.owner = owner;

        //setupBlackboard();
        root = buildTree();
        bind();
    }

    private void setupBlackboard() {

    }

    private Node buildTree() {
        return Nodes.repeater(
            Nodes.selector(
                /*Nodes.sequence(
                    Nodes.getFirstSpeciesInRange(15, Species.FOX, Species.EAGLE, Species.HUNTER),
                    Nodes.computePoint("escape"),
                    Nodes.getPathAndFollow()
                ),*/
                Nodes.sequence( // IF FOOD IN RANGE, GO EAT FOOD
                    Nodes.getFirstSpeciesInRange(10, Species.CARROT),
                    Nodes.getPathAndFollow(),
                    //Nodes.isTargetReached(),
                    Nodes.eat(Species.CARROT)
                ),
                /*Nodes.sequence(
                    Nodes.getFirstSpeciesInRange(10, Species.RABBIT),
                    Nodes.getPathAndFollow()
                    Nodes.isTargetReached(),
                    Nodes.reproduce()
                ),*/
                Nodes.sequence( // GET RANDOM POINT IN RANGE, GO TO POINT
                    Nodes.selector(
                        Nodes.sequence(
                            Nodes.isTargetValid(),
                            Nodes.inverter(Nodes.onTarget())
                        ),
                        Nodes.randomPoint(20)
                    ),
                    Nodes.getPathAndFollow()
                )
            )
        );
    }
}



























