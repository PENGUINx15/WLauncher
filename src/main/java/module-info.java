module me.penguin.wlauncher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens me.penguin.wlauncher to javafx.fxml;
    exports me.penguin.wlauncher;
}