package behaviour.tree;

import entities.Species;
import pathfinding.Point2D;

import java.util.List;

public class Blackboard {
    public Point2D target;
    public Point2D lastTarget;
    public Point2D randomTarget;
    public Point2D foodTarget;

    public List<Point2D> path;
    public List<Species> obstacles;
    public List<Species> food;

    public int visionRange;


}
