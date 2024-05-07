package lk.ijse.dep.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Customer;
import lk.ijse.dep.model.tm.CustomerTm;
import lk.ijse.dep.repository.CustomerRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerManagementFormController {


    // public TableView tblCustomer;
    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private AnchorPane rootNodeCus;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtCustomerAddress;

    @FXML
    private TextField txtCustomerContact;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCus.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerContact.setText("");
        txtCustomerAddress.setText("");

    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> objects = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getC_name(),
                        customer.getEmail(),
                        customer.getContact_num(),
                        customer.getAddress()
                );

                objects.add(tm);
            }

            tblCustomer.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();


        try {
            boolean isDelete = CustomerRepo.delete(customerId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }


    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String c_name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String contact_num = txtCustomerContact.getText();
        String address = txtCustomerAddress.getText();

        Customer customer = new Customer(customerId, c_name, email, contact_num, address);

        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String customerId = txtCustomerId.getText();
        String c_name = txtCustomerName.getText();
        String email = txtCustomerEmail.getText();
        String contact_num = txtCustomerContact.getText();
        String address = txtCustomerAddress.getText();
        System.out.println(customerId);

//        Customer customer = new Customer(customerId, c_name, email, contact_num, address);
//        CustomerRepo.update2(customerId,c_name
//        ,email,contact_num,address);
        try {
            boolean isUpdated = CustomerRepo.update2(customerId,c_name
                    ,email,contact_num,address);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "customer  didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtCustomerId.getText();

        Customer customer = CustomerRepo.searchById(id);
        if (customer != null) {
            txtCustomerId.setText(customer.getCustomerId());
            txtCustomerName.setText(customer.getC_name());
            txtCustomerEmail.setText(customer.getEmail());
            txtCustomerContact.setText(customer.getContact_num());
            txtCustomerAddress.setText(customer.getAddress());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();

        }
    }
}
