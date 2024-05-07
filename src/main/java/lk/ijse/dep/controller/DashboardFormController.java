package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardFormController {

    public AnchorPane rootDashboard;
    public Label lblCustomerCount;
    public Label lblOrderCount;
    public BarChart<String, Number> chart;





    public void btnCustomerOnAction(ActionEvent actionEvent) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/customerManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();

    }

    public void btnCustomerServiceOnAction(ActionEvent actionEvent) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/customerService_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("CustomerService Form");
        stage.centerOnScreen();
    }

    public void btnPaymentOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/payment_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Payment Form");
        stage.centerOnScreen();
    }

    public void btnDeliveryOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/deliveryManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Delivery Form");
        stage.centerOnScreen();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/supplierManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
    }

    public void btnItemOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/itemManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/EmployeeManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Employee Form");
        stage.centerOnScreen();
    }

    public void btnOrderOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/orderManagement_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Order Form");
        stage.centerOnScreen();
    }

   /* public void btnLogoutOnAction(ActionEvent actionEvent)  {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootDashboard.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }*/

    public void btnRegistrationOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/register_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Register Form");
        stage.centerOnScreen();

        /*Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");
        stage.show();*/
    }

    public void btnExitOnAction(ActionEvent actionEvent)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootDashboard.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }
}
