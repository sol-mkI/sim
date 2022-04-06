package behaviour.nodes;

import behaviour.nodes.actions.*;
import behaviour.nodes.composites.Selector;
import behaviour.nodes.composites.Sequence;
import behaviour.nodes.conditionals.*;
import behaviour.nodes.decorators.Inverter;
import behaviour.nodes.decorators.RandomChance;
import behaviour.nodes.decorators.Repeater;
import entities.Species;

import java.util.Arrays;

/**
 * Node creation & composition shortcut class.
 */
public class Nodes {

    /** Composites */

    public static Node repeater(Node child) {
        Repeater repeater = new Repeater();
        repeater.child = child;
        return repeater;
    }

    public static Node sequence(Node... child) {
        Sequence sequence = new Sequence();
        sequence.children.addAll(Arrays.asList(child));
        return sequence;
    }
    public static Node selector(Node... child) {
        Selector selector = new Selector();
        selector.children.addAll(Arrays.asList(child));
        return selector;
    }

    /** Decorators */

    public static Node inverter(Node child) {
        Inverter inverter = new Inverter();
        inverter.child =child;
        return inverter;
    }

    public static Node randomChance(Node reproduce, double chance) {
        RandomChance randomChance = new RandomChance(chance);
        randomChance.child = reproduce;
        return randomChance;
    }

    /** Subtrees */

    public static Node getPathAndFollow() {
        return sequence(
                getPath(),
                followPath()
        );
    }

    public static Node doCarrot() {
        return
        repeater(
            sequence(
                recieveDamage(1),
                randomChance(reproduce(), 0.09),
                sequence(
                    isAttLT("health", 0),
                    die()
                )
            )
        );
    }

    /** Actions & Conditions */

    public static Node die() {return new Die();}
    public static Node isAttLT(String att, int i) {return new IsAttLT(att, i);}
    public static Node recieveDamage(int i) {return new DecreaseAtt("",i);}
    public static Node waitTicks(int i) {return new Wait(i);}
    public static Node doesTargetExist() {return new DoesTargetExist();}
    public static Node isTargetValid(Species carrot) {return new IsTargetValid(carrot);}
    public static Node onTarget() {return new OnTarget();}
    public static Node foodInRange(int range, Species... food) {return new SpeciesInRange(range, food);}
    public static Node randomPoint() {return new GetRandomPoint();}
    public static Node getPath() {return new GetPath();}
    public static Node followPath() {return new FollowPath();}
    public static Node eat(Species... species) {return new EatSpecies(species);}
    public static Node getFirstSpeciesInRange(int range, Species... species) {return new SpeciesInRange(range, species);}
    public static Node reproduce() {return new Reproduce();}

}
