package org.practiceauthorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.Objects;

public class SignUpController extends MainController {
    @FXML
    TextField Email, Name;
    @FXML
    PasswordField Password;

    @FXML
    public void SignUpUser(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "root");
            Statement statement = connection.createStatement();

            String email = Email.getText();
            String name = Name.getText();
            String password = Password.getText();

            if(!(Objects.equals(email, "") && Objects.equals(name, "") && Objects.equals(password, ""))){
                String query = "SELECT email FROM users.users_table;";
                boolean found = false;

                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next())
                    if (Objects.equals(resultSet.getString(1), email)) found = true;

                try { resultSet.close(); } catch(SQLException se) {}

                if(!found){
                    query = "INSERT INTO users.users_table (firstName, email, password, createdAt, updatedAt) VALUES ('"+name+"', '"+email+"', '"+password+"', CURDATE(), CURDATE());";
                    statement.execute(query);
                    CallAlert("tett", Alert.AlertType.INFORMATION);
                }
                else
                    CallAlert("tett", Alert.AlertType.WARNING);
            }
            try { connection.close(); } catch(SQLException se) {}
            try { statement.close(); } catch(SQLException se) {}
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
