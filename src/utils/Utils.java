package utils;

import environment.Point2D;

import java.util.Collection;

/**
 * Classe amb metodes utilitzats per la majoria de components.
 */
public class Utils {
    /**
     * Retorna un element T de la Colleccio c aleatoriament.
     */
    public static <T> T random(Collection<T> c) {
        int num = (int) (Math.random() * c.size());
        for (T t : c) if (--num < 0) return t;
        throw new AssertionError();
    }

    /**
     * Retorna la distancia euclidiana entre dos punts.
     */
    public static int distance(Point2D a, Point2D b) {
        int dx = Math.abs(b.getX() - a.getX());
        int dy = Math.abs(b.getY() - a.getY());
        int min = Math.min(dx, dy);
        int max = Math.max(dx, dy);
        return (int) (1.414 * min) + (max - min);
    }
}
