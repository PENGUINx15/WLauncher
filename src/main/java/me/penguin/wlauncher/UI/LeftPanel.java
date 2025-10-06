package me.penguin.wlauncher.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static me.penguin.wlauncher.UI.SVGLoader.loadSVG;

public class LeftPanel extends JPanel {

    public LeftPanel() throws Exception {
        setBackground(new Color(41, 41, 41));
        setPreferredSize(new Dimension(80, 0));
        setLayout(new BorderLayout());

        // Логотип
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.setBackground(getBackground());
        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png")));
        ImageIcon logoIcon = new ImageIcon(originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoPanel.add(logoLabel, BorderLayout.CENTER);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        logoLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://wcjava.fun"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        add(logoPanel, BorderLayout.NORTH);

        // Настройки
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(getBackground());
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        BufferedImage gearNormal = loadSVG("/gear.svg", 32, 32);
        BufferedImage gearFill = loadSVG("/gear-fill.svg", 32, 32);

        JButton settingsButton = new JButton(new ImageIcon(gearNormal));
        settingsButton.setFocusPainted(false);
        settingsButton.setBackground(getBackground());
        settingsButton.setBorderPainted(false);
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsButton.setIcon(new ImageIcon(gearFill));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsButton.setIcon(new ImageIcon(gearNormal));
            }
        });

        settingsButton.addActionListener(e -> System.out.println("Настройки открыты!"));
        settingsPanel.add(settingsButton);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(settingsPanel, BorderLayout.SOUTH);
    }
}
