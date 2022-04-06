package environment;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

/**
 * JAVAFX Container for a tile.
 */
public class TileContext extends StackPane {
    /**
     * Body of a tile.
     */
    private final Rectangle fill;
    /**
     * Color queue to select the color displayed.
     */
    private final Queue<Color> colorQueue = new LinkedList<>();

    /**
     * Constructor with tile position and size.
     * @param position Position of the tile.
     * @param size Size of the tile.
     */
    public TileContext(Point2D position, int size) {
        fill = new Rectangle(size, size);
        fill.setFill(Color.LIGHTGRAY);

        getChildren().addAll(fill);
        setTranslateX(position.getX() * size);
        setTranslateY(position.getY() * size);
    }

    /**
     * Sets tile color to default, if more colors are enqueued selects the last.
     */
    public void setColor(Color color) {
        fill.setFill(color);
        while (!colorQueue.isEmpty())
            fill.setFill(colorQueue.poll());
    }

    /**
     * Enqueues a color.
     */
    public void addColor(Color color) {
        colorQueue.add(color);
    }
}
