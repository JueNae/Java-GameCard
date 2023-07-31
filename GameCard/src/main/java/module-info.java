module com.gamecard.gamecard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gamecard.gamecard to javafx.fxml;
    exports com.gamecard.gamecard;
}