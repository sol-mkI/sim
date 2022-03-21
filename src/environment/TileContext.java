package environment;

import pathfinding.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.Queue;

public class TileContext extends StackPane {
    private final Rectangle fill;
    private final Queue<Color> colorStack = new LinkedList<>();

    public TileContext(Point2D position, int size) {
        fill = new Rectangle(size, size);
        fill.setFill(Color.LIGHTGRAY);

        getChildren().addAll(fill);
        setTranslateX(position.x * size);
        setTranslateY(position.y * size);
    }

    public void setColor(Color color) {
        fill.setFill(color);
        while (!colorStack.isEmpty())
            fill.setFill(colorStack.poll());
    }

    public void addColor(Color color) {
        colorStack.add(color);
    }
}
