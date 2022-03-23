package pathfinding;

public class Point2D {
    public int x;
    public int y;


    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        x = 0;
        y = 0;
    }

    public Point2D getLocation() {
        return new Point2D(x, y);
    }
    public void setLocation(Point2D p) {
        setLocation(p.x, p.y);
    }
    public void setLocation(int x, int y) {
        move(x, y);
    }
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2D point2D = (Point2D) o;

        if (x != point2D.x) return false;
        return y == point2D.y;
    }

    public String toString() {
        return getClass().getSimpleName() + "{x=" + x + ",y=" + y + "}";
    }

    public int distance(Point2D other) {
        int dx = Math.abs(other.x - x);
        int dy = Math.abs(other.y - y);

        int _min = Math.min(dx, dy);
        int _max = Math.max(dx, dy);

        return (int) (Math.sqrt(2) * _min) + (_max - _min);
    }

}
