package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dep.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterFormController {
    public TextField txtAdminId;
    public TextField txtUsername;
    public TextField txtPassword;

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String AdminId = txtAdminId.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            boolean isSaved = saveUser(AdminId,username,password);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Registered Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private boolean saveUser(String AdminId, String username, String password) throws SQLException {
        String sql = "INSERT INTO Admin VALUES (?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,AdminId);
        pstm.setString(2,username);
        pstm.setString(3,password);

        return pstm.executeUpdate() > 0;
    }
}
