module com.example.stegodiplomagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires com.almasb.fxgl.all;
    requires java.desktop;
    requires jsoup;

    opens com.example.stegodiplomagui to javafx.fxml;
    exports com.example.stegodiplomagui;
}