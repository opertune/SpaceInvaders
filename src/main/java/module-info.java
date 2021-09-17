module com.example.spaceinvaders {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens fr.romain.spaceinvaders to javafx.fxml;
    exports fr.romain.spaceinvaders;
}