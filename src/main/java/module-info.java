module org.example.medisearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.medisearch to javafx.fxml;
    exports org.example.medisearch;
}