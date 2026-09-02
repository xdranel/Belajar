module com.myapp.hellofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.myapp.hellofx to javafx.fxml;
    exports com.myapp.hellofx;
}