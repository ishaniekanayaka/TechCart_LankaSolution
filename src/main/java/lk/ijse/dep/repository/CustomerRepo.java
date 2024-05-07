package lk.ijse.dep.repository;

import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {

    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO Customer VALUES(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getEmail());
        pstm.setObject(4, customer.getContact_num());
        pstm.setObject(5, customer.getAddress());

        return pstm.executeUpdate() > 0;

    }

    public static Customer searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String c_name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact_num = resultSet.getString(4);
            String address = resultSet.getString(5);

            Customer customer = new Customer(customerId, c_name, email, contact_num, address);

            return customer;
        }

        return null;
    }

    public static boolean update(Customer customer) throws SQLException {

        String sql = "UPDATE Customer SET c_name = ?, email = ?, contact_num = ?, address = ? WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getEmail());
        pstm.setObject(4, customer.getContact_num());
        pstm.setObject(5, customer.getAddress());


        return pstm.executeUpdate() > 0;
    }


    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            String c_name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact_num = resultSet.getString(4);
            String address = resultSet.getString(5);


            Customer customer = new Customer(customerId, c_name, email, contact_num, address);
            cusList.add(customer);
        }
        return cusList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT customerId FROM Customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String customerId = resultSet.getString(1);
            idList.add(customerId);
        }
        return idList;
    }

    public static boolean delete(String customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, customerId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update2(String customerId, String cName, String email, String contactNum, String address) throws SQLException {
        String sql = "UPDATE Customer SET c_name = ?, email = ?, contact_num = ?, address = ? WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, cName);
        pstm.setObject(2, email);
        pstm.setObject(3, contactNum);
        pstm.setObject(4, address);
        pstm.setObject(5 ,customerId);
        System.out.println("hiii");
        return pstm.executeUpdate() > 0;
    }

}

