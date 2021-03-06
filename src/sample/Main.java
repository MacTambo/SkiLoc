package sample;

import connectDb.Connect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
//84
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Connect connection = new Connect();
        connection.connect();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("SkiLoc");
        primaryStage.setMaximized(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
