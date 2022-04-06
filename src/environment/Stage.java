package environment;

import javafx.scene.layout.Pane;
import java.util.Random;

/**
 * Class to build a tile graph, generates tiles and its context.
 */
public class Stage extends TileGraph {

    /**
     * Size of every tile.
     */
    private final int tileSize;

    /**
     * Constructor.
     * @param x n horizontal pixels
     * @param y n vertical pixels
     * @param nx n tiles horizontally
     * @param ny n tiles vertically
     */
    public Stage(int x, int y, int nx, int ny) {
        super(new Point2D(nx,ny));
        env = new Tile[bounds.getX()][bounds.getY()];
        tileSize = Math.max(1, x/nx);

        context = new Pane();
        context.setPrefSize(x, y);
        generateTiles();
    }

    /**
     * Generates the tiles and its context.
     */
    private void generateTiles() {
        for (int i = 0; i < bounds.getX(); i++) {
            for (int j = 0; j < bounds.getY(); j++) {
                Tile tile = new Tile( new Random(System.nanoTime()).nextDouble() > 0.1,
                            new Point2D(i,j),
                        tileSize);

                env[i][j] = tile;
                context.getChildren().add(env[i][j].getContext());
            }
        }
    }
}