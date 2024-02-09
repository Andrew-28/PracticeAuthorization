package org.practiceauthorization;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    public void OpenSignUpWindow(ActionEvent event) {
        OpenNewWindow("signup-view.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    public void OpenWelcomeWindow(ActionEvent event) {
        OpenNewWindow("welcome-view.fxml");
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    @FXML
    public void OpenNewWindow(String s) {
        try {
            Stage stage = new Stage();
            stage.setTitle("Application");
            stage.setScene(new Scene(FXMLLoader.load(AuthorizationApplication.class.getResource(s))));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CallAlert(String text, Alert.AlertType alertType){
        Alert alert = new Alert(alertType);
        alert.setTitle("Alert");
        alert.setHeaderText(text);
        alert.showAndWait();
    }
}
