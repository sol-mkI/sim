package exceptions;
import environment.Point2D;

/**
 * Exception for when a position outside bounds is accessed.
 */
public class CoordinateNotValidException extends RuntimeException {

    public CoordinateNotValidException(Point2D p) {
        super("Point{x= " +p.getX()+", y= "+p.getY()+"}");
    }
}
