module com.example.raccoonloveisland {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.raccoonloveisland to javafx.fxml;
    exports com.example.raccoonloveisland;
}