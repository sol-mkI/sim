package behaviour.tree;

import entities.Entity;
import entities.Species;
import environment.Tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collection of values used in a behaviour tree for an entity within a TileEnvironment.
 */
public class Blackboard {

    public Map<Species, Integer> foodValues = new HashMap<>();

    public Entity target;
    public List<Tile> path;
}
