package lk.ijse.dep.repository;

import lk.ijse.dep.db.DbConnection;
import lk.ijse.dep.model.Delivery;
import lk.ijse.dep.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepo {
    public static boolean save(Delivery delivery) throws SQLException {
        String sql = "INSERT INTO Delivery VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, delivery.getDeliveryId());
        pstm.setObject(2, delivery.getDeliveryDate());
        pstm.setObject(3, delivery.getStatus());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Delivery delivery) throws SQLException {

        String sql = "UPDATE Delivery SET deliveryDate = ?, status = ? WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, delivery.getDeliveryId());
        pstm.setObject(2, delivery.getDeliveryDate());
        pstm.setObject(3, delivery.getStatus());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update2(String deliveryId, String deliveryDate, String status) throws SQLException {
        String sql = "UPDATE Delivery SET deliveryDate = ?, status = ?WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, deliveryDate);
        pstm.setObject(2, status);
        pstm.setObject(3, deliveryId);

        return pstm.executeUpdate() > 0;
    }

    public static Delivery searchById(String Id) throws SQLException {
        String sql = "SELECT * FROM Delivery WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, Id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String deliveryId = resultSet.getString(1);
            String deliveryDate = resultSet.getString(2);
            String status = resultSet.getString(3);

            Delivery delivery = new Delivery (deliveryId, deliveryDate, status);

            return delivery;
        }
        return null;
    }

    public static List<Delivery> getAll() throws SQLException {
        String sql = "SELECT * FROM Delivery";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery(sql);

        List<Delivery> deliveryList = new ArrayList<>();

        while (resultSet.next()) {
            String deliveryId = resultSet.getString(1);
            String deliveryDate = resultSet.getString(2);
            String status = resultSet.getString(3);

            Delivery delivery = new Delivery(deliveryId, deliveryDate, status);
            deliveryList.add(delivery);
        }
        return deliveryList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT deliveryId FROM Delivery";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String deliveryId= resultSet.getString(1);
            idList.add(deliveryId);
        }
        return idList;
    }
    public static boolean delete(String deliveryId) throws SQLException {
        String sql = "DELETE FROM Delivery WHERE deliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, deliveryId);

        return pstm.executeUpdate() > 0;
    }
}
