package utils;

import java.util.Collection;

public class Utils {
    public static <T> T random(Collection<T> c) {
        int num = (int) (Math.random() * c.size());
        for (T t : c) if (--num < 0) return t;
        throw new AssertionError();
    }
}
