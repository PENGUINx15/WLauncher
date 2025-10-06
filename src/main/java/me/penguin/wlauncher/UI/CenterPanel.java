package me.penguin.wlauncher.UI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CenterPanel extends JPanel {

    private JTextArea newsArea; // <- инициализируем

    public CenterPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Заголовок NEWS
        JPanel newsHeaderPanel = new JPanel(new BorderLayout());
        newsHeaderPanel.setOpaque(false);
        newsHeaderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        newsHeaderPanel.setPreferredSize(new Dimension(0, 50));

        JLabel newsTitle = new JLabel("NEWS");
        newsTitle.setForeground(Color.WHITE);
        newsTitle.setFont(new Font("Arial", Font.BOLD, 28));
        newsTitle.setBorder(BorderFactory.createEmptyBorder(0, 32, 0, 0));
        newsHeaderPanel.add(newsTitle, BorderLayout.WEST);

        JLabel moreButton = new JLabel("more news");
        moreButton.setForeground(Color.WHITE);
        moreButton.setFont(new Font("Arial", Font.PLAIN, 12));
        moreButton.setPreferredSize(new Dimension(120, 50));
        moreButton.setVerticalAlignment(SwingConstants.BOTTOM);
        moreButton.setHorizontalAlignment(SwingConstants.RIGHT);
        moreButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));
        newsHeaderPanel.add(moreButton, BorderLayout.EAST);

        add(newsHeaderPanel);

        // Создаём JTextArea и JScrollPane
        newsArea = new JTextArea();
        newsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        newsArea.setForeground(Color.LIGHT_GRAY);
        newsArea.setBackground(new Color(38, 38, 38, 180));
        newsArea.setEditable(false);
        newsArea.setLineWrap(true);
        newsArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(newsArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 32, 10, 32));

        add(scrollPane);

        // Загружаем последние 2 новости
        new Thread(this::loadNews).start();
    }

    private void loadNews() {
        try {
            URL url = new URL("https://wcjava.fun/newsfold/news.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            );
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }
            reader.close();


            Gson gson = new Gson();
            JsonArray newsArray = gson.fromJson(jsonText.toString(), JsonArray.class);

            StringBuilder newsText = new StringBuilder();
            int count = Math.min(2, newsArray.size()); // сколько новостей показывать
            for (int i = 0; i < count; i++) {
                JsonObject news = newsArray.get(i).getAsJsonObject();
                newsText.append(news.get("title").getAsString()).append("\n");

                JsonArray contentArray = news.getAsJsonArray("content");
                for (JsonElement lineEl : contentArray) {
                    newsText.append(lineEl.getAsString()).append("\n");
                }
                newsText.append("\n"); // раздел между новостями
            }

            SwingUtilities.invokeLater(() -> newsArea.setText(newsText.toString()));

        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> newsArea.setText("Не удалось загрузить новости."));
        }
    }
}
