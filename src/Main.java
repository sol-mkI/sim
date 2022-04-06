import behaviour.tree.BehaviourTree;
import behaviour.tree.TestCarrotBehaviour;
import behaviour.tree.TestRabbitBehaviour;
import entities.Entity;
import entities.animals.Rabbit;
import entities.vegetals.Carrot;
import entities.vegetals.Vegetal;
import environment.Stage;
import environment.Tile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import environment.Point2D;
import entities.EntityManager;
import simulation.SimulationManager;
import environment.TileGraph;

import java.util.Random;

public class Main extends Application {

    TileGraph te = new Stage(1000,1000,50,50);
    EntityManager em = new EntityManager(te);
    SimulationManager sm = new SimulationManager(te, em);
    @Override
    public void start(javafx.stage.Stage stage) {



        populateSimulation(50);

        Pane context = te.getContext();
        Scene scene = new Scene(context);

        Thread test = new Thread(() -> {

            long start = System.nanoTime();
            long rate = 30;
            for (long i = 0;; i++) {
                Platform.runLater(sm::update);
                //simulation.update();
                long end = start + i * 1000000000L / rate;
                //noinspection StatementWithEmptyBody
                while (System.nanoTime() < end); // wait
            }
        });

        test.start();
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void populateSimulation(int i) {
        Point2D p;
        Tile tile;
        boolean rabbit = false;
        for (int j=0; j<i;j++) {
            do {
                p = new Point2D(
                        new Random(System.nanoTime()).nextInt(te.getBounds().getX()-1),
                        new Random(System.nanoTime()).nextInt(te.getBounds().getY()-1));
                tile = te.getTile(p);
            } while (tile.isObstacle() || tile.getEntities().stream().anyMatch(e -> e instanceof Vegetal));
            new Random(System.nanoTime()).nextDouble();
            Entity carrot = new Carrot(new TestCarrotBehaviour(em));
            if (!rabbit) {
                Entity rabb = new Rabbit(new TestRabbitBehaviour(em));
                em.createEntity(rabb, tile.location());
                rabbit = true;
            }
            em.createEntity(carrot, tile.location());
        }
    }
}
