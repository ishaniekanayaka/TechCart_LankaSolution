package lk.ijse.dep.controller;

import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Employee;
import lk.ijse.dep.model.Service;
import lk.ijse.dep.model.tm.EmployeeTm;
import lk.ijse.dep.model.tm.ServiceTm;
import lk.ijse.dep.repository.EmployeeRepo;
import lk.ijse.dep.repository.ServiceRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceManagementForm {


    public AnchorPane rootNodeCusService;
    public TableView tblCustomerService;
    public TableColumn colServiceId;
    public TableColumn colContactNum;
    public TableColumn colDescription;
    public TextField txtServiceId;
    public TextField txtContact;
    public TextField txtDescription;

    public void txtSearchOnAction(ActionEvent actionEvent) {
       String Id = txtServiceId.getText();

        String sql = "SELECT * FROM CustomerService WHERE customerServiceId =?";

        try{
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, Id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                String contact_num = resultSet.getNString(3);
                String description = resultSet.getNString(4);


                txtContact.setText(contact_num);
                txtDescription.setText(description);

            } else {
                new Alert(Alert.AlertType.ERROR, "Service Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Service Id Not Found").show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNodeCusService.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String customerServiceId = txtServiceId.getText();
        String contact_num = txtContact.getText();
        String description = txtDescription.getText();

        Service service = new Service(customerServiceId,contact_num, description);

        try {
            boolean isSaved = ServiceRepo.save(service);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Service is Saved").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerServiceId = txtServiceId.getText();
        String contact_num = txtContact.getText();
        String description = txtDescription.getText();

        try {
            boolean isUpdated = ServiceRepo.update2(customerServiceId, contact_num, description);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "service  didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String customerServiceId = txtServiceId.getText();


        try {
            boolean isDelete = ServiceRepo.delete(customerServiceId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Service Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtServiceId.setText("");
        txtContact.setText("");
        txtDescription.setText("");
    }

  public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<ServiceTm> objects = FXCollections.observableArrayList();

        try {
            List<Service> serviceList = ServiceRepo.getAll();
            for (Service service : serviceList) {
                ServiceTm tm = new ServiceTm(
                        service.getCustomerServiceId(),
                        service.getContact_num(),
                        service.getDescription()
                );
                objects.add(tm);
            }
            tblCustomerService.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colServiceId.setCellValueFactory(new PropertyValueFactory<>("customerServiceId"));
        colContactNum.setCellValueFactory(new PropertyValueFactory<>("Contact_num"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
    }
}
