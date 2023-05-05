module com.example.nupogodi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.nupogodi to javafx.fxml;
    exports com.example.nupogodi;
}