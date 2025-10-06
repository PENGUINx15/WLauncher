package me.penguin.wlauncher.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanel extends JPanel {

    private final Image backgroundImage;

    public BackgroundPanel(String resourcePath) {
        ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(resourcePath)));
        backgroundImage = bgIcon.getImage();

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Масштабируем фон под размер панели
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Полупрозрачный оранжевый слой
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(245, 183, 89, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }
}
