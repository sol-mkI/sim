import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import simulation.Simulation;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Simulation simulation = new Simulation();
        simulation.populateSimulation(  50);

        Pane context = simulation.getGrid().getContext();
        Scene scene = new Scene(context);

        Thread test = new Thread(() -> {

            long start = System.nanoTime();
            long rate = 60;
            for (long i = 0;; i++) {
                Platform.runLater(simulation::update);
                //simulation.update();
                long end = start + i * 1000000000L / rate;
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
