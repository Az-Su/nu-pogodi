module com.example.nupogodi {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.nupogodi to javafx.fxml;
    exports com.example.nupogodi;
}