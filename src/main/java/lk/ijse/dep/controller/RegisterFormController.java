package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {

    public AnchorPane rootNodeRegister;
    public TextField txtUserId;
    public TextField txtPassword;
    public TextField txtUserName;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String userId = txtUserId.getText();
        String userName =txtUserName.getText();

        String password = txtPassword.getText();

        try {
            boolean isSaved = saveUser(userId,userName,password);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Registered Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean saveUser(String userId, String userName, String password) throws SQLException {
        String sql = "INSERT INTO User VALUES (?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userId);
        pstm.setString(2,userName);
        pstm.setString(3,password);

        return pstm.executeUpdate() > 0;
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeRegister.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}
