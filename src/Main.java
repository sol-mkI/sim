import com.sun.javafx.perf.PerformanceTracker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simulation.Simulation;


public class Main extends Application {
    private static PerformanceTracker tracker;

    private float getFPS () {
        float fps = tracker.getAverageFPS();
        tracker.resetAverageFPS();
        return fps;
    }

    @Override
    public void start(Stage stage) {
        Simulation simulation = new Simulation();
        simulation.populateSimulation(  10);
        Pane context = simulation.getGrid().getContext();

        /*VBox root = new VBox(20);
        Label label1 = new Label();
        Label label2 = new Label();
        root.getChildren().addAll(label1, label2);
        context.getChildren().addAll(root);*/

        Scene scene = new Scene(context);

        Thread test = new Thread(() -> {

            long start = System.nanoTime();
            long rate = 10;
            for (long i = 0;; i++) {
                Platform.runLater(simulation::update);
                long end = start + i * 1000000000L / rate;
                //noinspection StatementWithEmptyBody
                while (System.nanoTime() < end);
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
}
                /*long t0 = System.nanoTime();
                AtomicLong frame = new AtomicLong();
                simulation.update();
                long t1 = System.nanoTime();
                Platform.runLater(() -> label1.setText("F" + frame.incrementAndGet() + " >>> " + (t1 - t0) / 1e6 + " ms."));*/
