package entities;

import behaviour.tree.TestCarrotBehaviour;
import behaviour.tree.TestRabbitBehaviour;
import entities.animals.Rabbit;
import entities.vegetals.Carrot;
import environment.Tile;
import environment.TileGraph;
import javafx.scene.paint.Color;
import pathfinding.Node;
import pathfinding.Pathfinder;
import environment.Point2D;
import utils.Utils;

import java.util.*;

/**
 * Common operations for an external interface for any entity on an environment.
 */
public class EntityManager {
    /**
     * Environment
     */
    private final TileGraph env;
    /**
     * Pathfinder instance
     */
    private final Pathfinder pathfinder;

    /**
     * Constructor with environment.
     */
    public EntityManager(TileGraph env) {
        this.env = env;
        this.pathfinder = new Pathfinder(env);
    }
    /**
     * Tries to add an entity to the environment.
     */
    public void addEntity(Entity e) {
        Point2D p = e.position();
        env.getTile(p).addEntity(e);
    }
    /**
     * Removes an entity from the environment.
     */
    public void removeEntity(Entity e) {
        Point2D p = e.position();
        env.getTile(p).removeEntity(e);
    }
    /**
     * Tries to move an entity from a tile to another.
     * @param e Entity
     * @param t Destiny
     * @return success
     */
    public boolean moveEntity(Entity e, Point2D t) {
        Tile target = env.getTile(t);
        if (!target.canFit(e)) return false;

        Point2D o = e.position();
        Tile origin = env.getTile(o);

        target.addEntity(e);
        origin.removeEntity(e);
        e.setPosition(t);
        return true;
    }
    /**
     * A new instance of an entity gets added to the environment.
     * @param e Entity
     * @param p Entity position
     */
    public void createEntity(Entity e, Point2D p) {
        e.setPosition(p);
        addEntity(e);
    }
    /**
     * Checks whether there is already a species of its type in a tile.
     * @param p tile position
     * @param s species
     * @return success
     */
    public boolean speciesInPosition(Point2D p, Species s) {
        if (env.getTile(p).isObstacle()) return false;

        for (Entity entity : env.getTile(p).getEntities())
            if (entity.specie().equals(s))
                return false;
        return true;
    }
    /**
     * Creates an entity from a string with its class name.
     * @param s Entity class name
     * @param p Entity position
     */
    //TODO: Trobar millor manera ? #
    // 1 --> getClass().newInstance() no funciona
    public void createEntityFromString(String s, Point2D p) {
        Entity e = null;
        if (s.equals("Carrot"))
            e = new Carrot(new TestCarrotBehaviour(this));
        else if (s.equals("Rabbit"))
            e = new Rabbit(new TestRabbitBehaviour(this));

        assert e != null;
        e.setPosition(p);
        addEntity(e);
    }
    /**
     * Collects and returns all entities within the environment.
     */
    public PriorityQueue<Entity> preloadEntities() {
        PriorityQueue<Entity> qq = new PriorityQueue<>();
        for (Tile[] tileArray : env.getTiles())
            for (Tile tile: tileArray)
                for (Entity entity : tile.getEntities()) {
                    entity.computePriority(retrieveEntitiesInRange(entity));
                    qq.add(entity);
                }
        return qq;
    }
    /**
     * Returns a list with the entities the tile in a position.
     * @param p position
     */
    public List<Entity> retrieveEntities(Point2D p) {
        env.getTile(p).setColor(Color.DARKKHAKI);
        return env.getTile(p).getEntities();
    }
    /**
     * Returns the bounds of the environment.
     */
    //TODO: Aixo no hauria de ser aqui ?
    public Point2D bounds() {return env.getBounds();}

    /**
     * Checks whether an entity can move to a certain position.
     * @param e Entity
     * @param p Position
     * @return success
     */
    public boolean canMove(Entity e, Point2D p) {
        return isPositionValid(p) && !env.getTile(p).isObstacle() && env.getTile(p).canFit(e);
    }
    /**
     * Checks whether a position is valid in the environment.
     * @param p position
     * @return success
     */
    public boolean isPositionValid(Point2D p) {return env.isPositionInBounds(p);}
    /**
     * Requests a path for an entity to a position.
     * @param e Entity
     * @param t Destiny
     * @return path as a list of tiles
     */
    public List<Tile> findPath(Entity e, Point2D t) {
        List<Node> path = pathfinder.findPath(env.getNode(e.position()), env.getNode(t));
        List<Tile> tpath = new LinkedList<>();
        for (Node n : path) {
            Tile tile = (Tile) n;
            tpath.add(tile);
            tile.setColor(Color.AQUAMARINE);
        }
        return tpath;
    }
    /**
     * Gathers all entities within range.
     */
    public List<Detection> retrieveEntitiesInRange(Entity entity) {

        List<Detection> detectionList = new ArrayList<>();

        Point2D o = entity.position();
        int range = entity.visionRange();

        for (int i = o.getX() - range; i < o.getX() + range; i++)
            for (int j = o.getY() - range; j < o.getY() + range; j++) {
                Point2D t = new Point2D(i,j);
                int distance = Utils.distance(o, t);
                if (distance > range)
                    continue;
                if (!isPositionValid(t))
                    continue;


                for (Entity e : retrieveEntities(t)) {
                    Detection detection = new Detection(e, distance);
                    detectionList.add(detection);
                }
            }

        Collections.sort(detectionList);
        return detectionList;
    }
}
