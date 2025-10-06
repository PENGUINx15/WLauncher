package me.penguin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.penguin.UI.BackgroundPanel;
import me.penguin.UI.CenterPanel;
import me.penguin.UI.LeftPanel;
import me.penguin.UI.RightPanel;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) {
        BackgroundPanel root = new BackgroundPanel("/background.jpg");
        root.getChildren().addAll(
            new LeftPanel(),
            new CenterPanel(),
            new RightPanel()
        );

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WLauncher");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
