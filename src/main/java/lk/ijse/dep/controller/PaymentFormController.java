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
import lk.ijse.dep.model.Customer;
import lk.ijse.dep.model.Payment;
import lk.ijse.dep.model.tm.CustomerTm;
import lk.ijse.dep.model.tm.PaymentTm;
import lk.ijse.dep.repository.CustomerRepo;
import lk.ijse.dep.repository.PaymentRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PaymentFormController {
    public AnchorPane rootNodeCusPayment;

    public TextField txtPaymentId;
    public TextField txtAmount;
    public TextField txtMethod;
    public TextField txtDate;

    public TableColumn colAmount;
    public TableColumn colDate;
    public TableColumn colMethod;

    public TableView tblCustomerPayment;
    public TableColumn colPaymentId;


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String paymentId = txtPaymentId.getText();
        String amount = txtAmount.getText();
        String paymentDate = txtDate.getText();
        String method = txtMethod.getText();

         Payment payment = new Payment(paymentId, amount, paymentDate, method);

         try {
             boolean isSaved = PaymentRepo.save(payment);
             if (isSaved) {
                 new Alert(Alert.AlertType.CONFIRMATION, "Payment Saved!").show();
                 clearFields();
             }
             }catch (SQLException e){
             throw new RuntimeException(e);
         }
    }

    private void clearFields() {
        txtPaymentId.setText("");
        txtAmount.setText("");
        txtDate.setText("");
        txtMethod.setText("");

    }

    public  void  initialize(){
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<PaymentTm> objects = FXCollections.observableArrayList();

        try {
            List<Payment> paymentList = PaymentRepo.getAll();
            for (Payment payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getAmount(),
                        payment.getPaymentDate(),
                        payment.getMethod()
                );


                objects.add(tm);
            }

            tblCustomerPayment.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("PaymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("Method"));

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String paymentId = txtPaymentId.getText();


        try {
            boolean isDelete = PaymentRepo.delete(paymentId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Payment Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String paymentId = txtPaymentId.getText();
        String amount = txtAmount.getText();
        String paymentDate = txtDate.getText();
        String method = txtMethod.getText();

        try {
            boolean isUpdated = PaymentRepo.update2(paymentId, amount, paymentDate, method);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Payment update!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "payment didn't update!").show();

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCusPayment.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtPaymentId.getText();

        Payment payment = PaymentRepo.searchById(id);
        if (payment != null) {
           txtPaymentId.setText(payment.getPaymentId());
           txtAmount.setText(payment.getAmount());
           txtDate.setText(payment.getPaymentDate());
           txtMethod.setText(payment.getMethod());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();

        }
    }
}
