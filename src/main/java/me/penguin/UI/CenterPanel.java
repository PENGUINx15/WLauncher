package me.penguin.UI;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CenterPanel extends VBox {

    private final VBox newsBox = new VBox(10);

    public CenterPanel() {
        Label title = new Label("NEWS");
        getChildren().add(title);

        ScrollPane scroll = new ScrollPane(newsBox);
        scroll.setFitToWidth(true);
        getChildren().add(scroll);

        loadNews();
    }

    private void loadNews() {
        new Thread(() -> {
            try {
                URL url = new URL("https://wcjava.fun/newsfold/news.json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                InputStreamReader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                JsonArray newsArray = new Gson().fromJson(reader, JsonArray.class);
                reader.close();

                Platform.runLater(() -> {
                    int count = Math.min(2, newsArray.size());
                    for (int i = 0; i < count; i++) {
                        JsonObject news = newsArray.get(i).getAsJsonObject();
                        StringBuilder text = new StringBuilder(news.get("title").getAsString() + "\n");

                        JsonArray contentArray = news.getAsJsonArray("content");
                        for (JsonElement line : contentArray) {
                            text.append(line.getAsString()).append("\n");
                        }

                        newsBox.getChildren().add(new Label(text.toString()));
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
