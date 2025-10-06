package me.penguin.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class BackgroundPanel extends Pane {

    public BackgroundPanel(String resourcePath) {
        // Загружаем изображение фона
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(resourcePath)));
        ImageView bgView = new ImageView(bgImage);

        // Растягиваем изображение на весь Pane
        bgView.setFitWidth(1280); // можно менять на bind с размером Pane
        bgView.setFitHeight(720);
        bgView.setPreserveRatio(false);

        // Полупрозрачный оранжевый слой
        Rectangle overlay = new Rectangle(1280, 720, Color.rgb(245, 183, 89, 0.6));

        // Добавляем все в Pane
        getChildren().addAll(bgView, overlay);

        // Можно сделать биндинг размеров, чтобы фон и overlay растягивались при изменении размеров окна
        widthProperty().addListener((obs, oldVal, newVal) -> {
            bgView.setFitWidth(newVal.doubleValue());
            overlay.setWidth(newVal.doubleValue());
        });
        heightProperty().addListener((obs, oldVal, newVal) -> {
            bgView.setFitHeight(newVal.doubleValue());
            overlay.setHeight(newVal.doubleValue());
        });
    }
}
