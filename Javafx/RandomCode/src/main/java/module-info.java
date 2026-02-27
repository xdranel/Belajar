module com.myapp.randomcode {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.myapp.randomcode to javafx.fxml;
    exports com.myapp.randomcode;
}