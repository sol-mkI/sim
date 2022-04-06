package behaviour.tree;

import behaviour.nodes.Nodes;
import behaviour.nodes.Node;
import entities.Entity;
import entities.Species;
import entities.EntityManager;

/**
 * Test behaviour
 */
public class TestRabbitBehaviour extends BehaviourTree {

    /**
     * Constructor
     */
    public TestRabbitBehaviour(EntityManager entityManager) {
        super(entityManager);

        setupBlackboard();
        root = buildTree();
        traverse(root);
    }

    /**
     * Init blackboard
     */
    private void setupBlackboard() {
        bb.foodValues.put(Species.CARROT, 50);
    }

    /**
     * Build tree sequence.
     */
    private Node buildTree() {
        return Nodes.repeater(
            Nodes.selector(
                Nodes.sequence(
                    Nodes.recieveDamage(1),
                    Nodes.sequence(
                        Nodes.isAttLT("health", 0),
                        Nodes.die()
                    )
                ),
                /*Nodes.sequence(
                    Nodes.getFirstSpeciesInRange(15, Species.FOX, Species.EAGLE, Species.HUNTER),
                    Nodes.computePoint("escape"),
                    Nodes.getPathAndFollow()
                ),*/
                Nodes.sequence( // IF FOOD IN RANGE, GO EAT FOOD
                     Nodes.selector(
                         Nodes.isTargetValid(Species.CARROT),
                         Nodes.getFirstSpeciesInRange(10, Species.CARROT)
                     ),
                     Nodes.selector(
                         Nodes.onTarget(),
                         Nodes.getPathAndFollow()
                     ),
                    //Nodes.getPathAndFollow(),
                    //Nodes.isTargetReached(),
                     Nodes.eat(Species.CARROT),
                     Nodes.randomChance(Nodes.reproduce(), 0.1)
                    //Nodes.eat(Species.CARROT)
                )/*,
                Nodes.sequence(
                    Nodes.waitTicks(5),
                    Nodes.getFirstSpeciesInRange(5, Species.RABBIT),
                    //Nodes.inverter(//Nodes.targetInRange(5),
                    //Nodes.inverter(Nodes.onTarget()),
                    Nodes.getPathAndFollow()
                    //Nodes.isTargetReached(),
                    //Nodes.reproduce()
                )*/,
                Nodes.sequence( // GET RANDOM POINT IN RANGE, GO TO POINT
                    Nodes.selector(
                        Nodes.sequence(
                            Nodes.doesTargetExist(),
                            Nodes.inverter(Nodes.onTarget())
                        ),
                        Nodes.randomPoint()
                    ),
                    Nodes.getPathAndFollow()
                )
            )
        );
    }
}



























