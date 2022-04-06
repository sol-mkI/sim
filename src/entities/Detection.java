package entities;

public class Detection implements Comparable<Detection> {
    private final Entity entity;
    private final int distance;

    public Detection(Entity entity, int distance) {
        this.entity = entity;
        this.distance = distance;
    }

    public Entity detected() {return entity;}
    public int distance() {return distance;}

    @Override
    public int compareTo(Detection o) {
        return Integer.compare(distance, o.distance);
    }
}
