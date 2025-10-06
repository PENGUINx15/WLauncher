package me.penguin.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class RightPanel extends VBox {

    public RightPanel() {
        setPadding(new Insets(20));
        setSpacing(10);
        setPrefWidth(250); // ширина панели
        setStyle("-fx-background-color: rgba(0,0,0,0.3);"); // полупрозрачный фон

        setAlignment(Pos.TOP_LEFT);

        // Пример меток (можно заменить на твои элементы)
        Label versionLabel = new Label("Version: 1.0");
        Label statusLabel = new Label("Status: Online");
        Label infoLabel = new Label("Additional info here");

        versionLabel.setStyle("-fx-text-fill: white;");
        statusLabel.setStyle("-fx-text-fill: white;");
        infoLabel.setStyle("-fx-text-fill: white;");

        getChildren().addAll(versionLabel, statusLabel, infoLabel);
    }
}
