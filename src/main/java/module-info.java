module org.practiceauthorization {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens org.practiceauthorization to javafx.fxml;
    exports org.practiceauthorization;
}