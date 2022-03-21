package exceptions;
import pathfinding.Point2D;

public class CoordinateNotValidException extends RuntimeException {

    public CoordinateNotValidException(Point2D p) {
        super("Point{x= " +p.x+", y= "+p.y+"}");
    }
}
