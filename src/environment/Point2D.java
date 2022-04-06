package environment;

/**
 * Represents a point in an integer-represented two-dimensional space.
 */
public class Point2D {
    private int x;
    private int y;

    /**
     * Default constructor, position at 0,0
     */
    public Point2D() {
        this(0, 0);
    }

    /**
     * Parametrized constructor, position at x,y
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x value.
     * @return x
     */
    public int getX() {return x;}

    /**
     * Getter for y value.
     * @return y
     */
    public int getY() {return y;}

    /**
     * Returns a copy.
     * @return Point2D
     */
    public Point2D getLocation() {
        return new Point2D(x, y);
    }

    /**
     * Setter for the position in x,y
     * @param x x
     * @param y y
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns whether this position is equal to o's.
     * @param o Object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point2D = (Point2D) o;

        if (x != point2D.x) return false;
        return y == point2D.y;
    }

    /**
     * Returns a string with the components of this position
     */
    public String toString() {
        return getClass().getSimpleName() + "{x=" + x + ",y=" + y + "}";
    }

    /*public int distance(Point2D other) {
        int dx = Math.abs(other.x - x);
        int dy = Math.abs(other.y - y);

        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);

        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }*/

    /**
     * Adds two points together
     * @param p Point2D
     */
    public void add(Point2D p) {
        this.x += p.x;
        this.y += p.y;
    }

    /**
     * Adds two points together
     * @param a Point2D
     * @param b Point2D
     */
    public void add(int a, int b) {
        this.x += a;
        this.y += b;
    }

}
