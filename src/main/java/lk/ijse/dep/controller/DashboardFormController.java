package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    public AnchorPane rootDashboard;
    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblOrderCount;

    @FXML
    void btnCustomerManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomerServiceOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeliveryManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeeManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootDashboard.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }

    @FXML
    void btnOrderManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentManagementOnAction(ActionEvent event) {

    }

    @FXML
    void btnSupplierManagementOnAction(ActionEvent event) {

    }

}
