package lk.ijse.dep.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.model.Delivery;
import lk.ijse.dep.model.Payment;
import lk.ijse.dep.model.tm.DeliveryTm;
import lk.ijse.dep.model.tm.PaymentTm;
import lk.ijse.dep.repository.DeliveryRepo;
import lk.ijse.dep.repository.PaymentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeliveryManagementFormController {
    public TextField txtDeliveryId;

    public TextField txtStatus;

    public TableColumn colDeliveryId;
    public TableColumn colStatus;
    public AnchorPane rootNodeCusDelivery;
    public TableView tblCustomerPayment;
    public TableColumn colDate;
    public TextField txtDate;
    public TableView tblCustomerDelivery;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCusDelivery.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
    public  void  initialize(){
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<DeliveryTm> objects = FXCollections.observableArrayList();

        try {
            List<Delivery> deliveryList = DeliveryRepo.getAll();
            for (Delivery delivery : deliveryList) {
                DeliveryTm tm = new DeliveryTm(
                        delivery.getDeliveryId(),
                        delivery.getDeliveryDate(),
                        delivery.getStatus()
                );


                objects.add(tm);
            }

            tblCustomerDelivery.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colDeliveryId.setCellValueFactory(new PropertyValueFactory<>("DeliveryId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("DeliveryDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String deliveryId = txtDeliveryId.getText();


        try {
            boolean isDelete = DeliveryRepo.delete(deliveryId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String deliveryId= txtDeliveryId.getText();
        String deliveryDate = txtDate.getText();
        String status = txtStatus.getText();

        try {
            boolean isUpdated = DeliveryRepo.update2(deliveryId, deliveryDate, status);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Delete update!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Delivery didn't update!").show();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String deliveryId = txtDeliveryId.getText();
        String deliveryDate = txtDate.getText();
        String status = txtStatus.getText();

        Delivery delivery = new Delivery(deliveryId, deliveryDate, status);

        try {
            boolean isSaved = DeliveryRepo.save(delivery);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Delivery Saved!").show();
                clearFields();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtDeliveryId.setText("");
        txtDate.setText("");
        txtStatus.setText("");

    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtDeliveryId.getText();

        Delivery delivery = DeliveryRepo.searchById(id);
        if (delivery != null) {
            txtDeliveryId.setText(delivery.getDeliveryId());
            txtDate.setText(delivery.getDeliveryDate());
            txtStatus.setText(delivery.getStatus());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "delivery not found!").show();

        }
    }
}
