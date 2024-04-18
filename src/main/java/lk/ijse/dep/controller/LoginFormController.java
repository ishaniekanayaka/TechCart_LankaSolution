package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private AnchorPane rootNode;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String userId = txtId.getText();
        String password = txtPassword.getText();

        try {
            checkCredentials(userId, password);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void checkCredentials(String userId, String password) throws SQLException, IOException {
        String sql = "SELECT userName,password FROM Admin WHERE adminId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,userId);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String Password = resultSet.getString("Password");
            if (password.equals(Password)){
                navigateToDashboard();
            }else {
                new Alert(Alert.AlertType.ERROR,"Sorry! Password is incorrect try again ");
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Sorry Username Can't be find").show();
        }
    }

    private void navigateToDashboard() throws IOException {
         Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

         Scene scene = new Scene(rootNode);
         Stage stage = (Stage) this.rootNode.getScene().getWindow();
         stage.setScene(scene);
         stage.centerOnScreen();
         stage.setTitle("Dashboard");

    }

    @FXML
    void linkRegistrationOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/register_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");
        stage.show();
    }

}