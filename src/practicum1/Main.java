package practicum1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import practicum1.DataProcessing.Containers.ProcessList;
import practicum1.DataProcessing.Processing.XMLProcessor;
import practicum1.Simulation.ScheduleAlgorithms.*;
import practicum1.Simulation.SimulationManager;

public class Main extends Application {

    private SimulationManager simulationManager;


    //keep a reference to the main  window controller
    public MainWindowViewController mainwindow;


    /**
     *  SETUP
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.setupSimulationManager();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        //provide the controller with a reference of the simulationManager
        loader.setControllerFactory( c -> {
            if(c == MainWindowViewController.class) {
                MainWindowViewController mc = new MainWindowViewController();
                mc.setSimulationManager(this.simulationManager);
                mainwindow = mc;
                return mc;
            }else{
                try {
                    return c.newInstance();
                }catch (Exception exc){
                    throw new RuntimeException(exc);
                }
            }
        });


        Parent flowPane = loader.load();

        primaryStage.setTitle("Scheduler");
        //primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(flowPane, 1000, 700));
        primaryStage.show();
        this.simulationManager.setMainWindowController(mainwindow);
        mainwindow.scene = primaryStage.getScene();
        this.mainwindow.startFinished();



        /**
         * TESTING
         * - - - - - - - -
         *
         * Use below to test setting up objects etc.
         * - - - - - - - -
         */



    }


    //Sets up the simulationManager that controllers simulations
    public void setupSimulationManager() {
        this.simulationManager = new SimulationManager();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
