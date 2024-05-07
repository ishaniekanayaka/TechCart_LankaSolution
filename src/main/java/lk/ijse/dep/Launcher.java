package lk.ijse.dep;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage stage) throws IOException {
      // stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/login_form.fxml"))));
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml")))); // loin form eke idn load wel enn on nm meka delete kaala ud ek dagann
        stage.setTitle("Login form");
        stage.centerOnScreen();
        stage.show();
    }
}
