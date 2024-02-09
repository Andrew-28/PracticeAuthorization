package org.practiceauthorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Objects;

public class SignInController extends MainController {
    @FXML
    TextField Email;
    @FXML
    PasswordField Password;
    @FXML
    public void SignInUser(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
            Statement statement = connection.createStatement();

            String query = "SELECT email, password FROM users.users_table;";


            ResultSet resultSet = statement.executeQuery(query);
            String email = Email.getText();
            String password = Password.getText();
            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString(1), email))
                    if (Objects.equals(resultSet.getString(2), password))
                        OpenWelcomeWindow(event);
            }

            try { connection.close(); } catch(SQLException se) {}
            try { statement.close(); } catch(SQLException se) {}
            try { resultSet.close(); } catch(SQLException se) {}
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
