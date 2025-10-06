package me.penguin.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftPanel extends VBox {

    public LeftPanel() {
        setPadding(new Insets(20));
        setSpacing(15);
        setPrefWidth(250); // ширина панели
        setStyle("-fx-background-color: rgba(0,0,0,0.3);"); // полупрозрачный фон

        setAlignment(Pos.TOP_CENTER);

        // Пример кнопок (можно заменить на твои)
        Button playButton = new Button("Play");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        playButton.setMaxWidth(Double.MAX_VALUE);
        settingsButton.setMaxWidth(Double.MAX_VALUE);
        exitButton.setMaxWidth(Double.MAX_VALUE);

        getChildren().addAll(playButton, settingsButton, exitButton);
    }
}
