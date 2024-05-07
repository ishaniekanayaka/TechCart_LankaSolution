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
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Customer;
import lk.ijse.dep.model.Employee;
import lk.ijse.dep.model.tm.EmployeeTm;
import lk.ijse.dep.repository.CustomerRepo;
import lk.ijse.dep.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeManagementFormController {
    public TextField txtEmployeeId;
    public TextField txtName;
    public TextField txtPosition;
    public TextField txtContactNum;
    public TextField txtEmail;
    public TextField txtSalary;
    public TableColumn colEmployeeId;
    public TableColumn colName;
    public TableColumn colPosition;
    public TableColumn colContact_num;
    public TableColumn colEmail;
    public TableColumn colSalary;
    public TableView tblEmployeeManagement;
    public AnchorPane rootNoteEmployee;


    public void txtSearchOnAction(ActionEvent actionEvent) {
        String Id = txtEmployeeId.getText();

        String sql = "SELECT * FROM Employee WHERE employeeId =?";

        try{
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, Id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()){
                String salary = resultSet.getNString(3);
                String e_name = resultSet.getNString(4);
                String position = resultSet.getNString(5);
                String contact_num = resultSet.getNString(6);
                String email = resultSet.getNString(7);

                txtSalary.setText(salary);
                txtName.setText(e_name);
                txtPosition.setText(position);
                txtContactNum.setText(contact_num);
                txtEmail.setText(email);
            } else {
                new Alert(Alert.AlertType.ERROR, "Employee Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Employee Id Not Found").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String salary = txtSalary.getText();
        String e_name = txtName.getText();
        String position = txtPosition.getText();
        String contact_num = txtContactNum.getText();
        String email = txtEmail.getText();

        Employee employee = new Employee(employeeId, salary, e_name,position,contact_num,email);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Employee is Saved").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtSalary.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtContactNum.setText("");
        txtEmail.setText("");
    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<EmployeeTm> objects = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getSalary(),
                        employee.getE_name(),
                        employee.getPosition(),
                        employee.getContact_num(),
                        employee.getEmail()
                );
                objects.add(tm);
            }
            tblEmployeeManagement.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        colName.setCellValueFactory(new PropertyValueFactory<>("E_name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        colContact_num.setCellValueFactory(new PropertyValueFactory<>("Contact_num"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();
        String salary = txtSalary.getText();
        String e_name = txtName.getText();
        String position = txtPosition.getText();
        String contact_num = txtContactNum.getText();
        String email = txtEmail.getText();

        try {
            boolean isUpdated = EmployeeRepo.update2(employeeId, salary, e_name, position, contact_num, email);

            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "employee  didnt updated!").show();
            }

        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String employeeId = txtEmployeeId.getText();


        try {
            boolean isDelete = EmployeeRepo.delete(employeeId);
            if (isDelete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNoteEmployee.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}
