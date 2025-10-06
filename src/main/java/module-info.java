module me.penguin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires gson;


    opens me.penguin to javafx.fxml;
    exports me.penguin;
}