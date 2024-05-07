package lk.ijse.dep.repository;

import javafx.scene.control.Alert;
import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Customer;
import lk.ijse.dep.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employee (employeeId,salary,e_name,position,contact_num,email) VALUES(?,?,?,?,?,?)";



        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getSalary());
        pstm.setObject(3, employee.getE_name());
        pstm.setObject(4, employee.getPosition());
        pstm.setObject(5, employee.getContact_num());
        pstm.setObject(6, employee.getEmail());

        return pstm.executeUpdate() > 0;
    }



    public static Employee searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM  WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String salary = resultSet.getString(2);
            String e_name = resultSet.getString(3);
            String position = resultSet.getString(4);
            String contact_num = resultSet.getString(5);
            String email= resultSet.getString(6);

            Employee employee = new Employee(employeeId, salary, e_name, position, contact_num, email);

            return employee;
        }

        return null;
    }
    public static boolean update(Employee employee) throws SQLException {

        String sql = "UPDATE Employee SET salary = ?, e_name = ?, position = ?, contact_num = ?, email = ? WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getSalary());
        pstm.setObject(3, employee.getE_name());
        pstm.setObject(4, employee.getPosition());
        pstm.setObject(5, employee.getContact_num());
        pstm.setObject(5, employee.getEmail());


        return pstm.executeUpdate() > 0;
    }

    public static boolean delete(String employeeId) throws SQLException {
        String sql = "DELETE FROM Employee WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update2(String employeeId, String salary, String e_name, String position, String contact_num, String email) throws SQLException {
        String sql = "UPDATE Employee SET salary = ?, e_name = ?, position = ?, contact_num = ?, email = ? WHERE employeeId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, salary);
        pstm.setObject(2, e_name);
        pstm.setObject(3, position);
        pstm.setObject(4 ,contact_num);
        pstm.setObject(5 ,email);
        pstm.setObject(6, employeeId);

        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Employee> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String salary = resultSet.getString(3);
            String e_name = resultSet.getString(4);
            String position = resultSet.getString(5);
            String contact_num = resultSet.getString(6);
            String email = resultSet.getString(7);


            Employee employee = new Employee(employeeId, salary, e_name, position, contact_num, email);
            cusList.add(employee);
        }
        return cusList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT employeeId FROM Employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String employeeId= resultSet.getString(1);
            idList.add(employeeId);
        }
        return idList;
    }
}
