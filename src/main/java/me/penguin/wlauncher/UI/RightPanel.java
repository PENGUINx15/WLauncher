package me.penguin.wlauncher.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static me.penguin.UI.SVGLoader.loadSVG;
import me.penguin.StartClient;

public class RightPanel extends JPanel {

    public static JButton playButton; // Статическая кнопка Play

    public RightPanel() throws Exception {
        setBackground(new Color(26, 26, 26, 150));
        setOpaque(true);
        setPreferredSize(new Dimension(330, 0));
        setLayout(new BorderLayout());

        // Верхняя часть с профилем и кнопкой выхода
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 15, 30));
        topPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;

        JButton profileButton = new JButton("PENGUINx13");
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setBackground(new Color(61, 61, 61));
        profileButton.setForeground(Color.WHITE);
        profileButton.setFont(new Font("Dialog", Font.BOLD, 20));
        profileButton.setPreferredSize(new Dimension(200, 50));
        gbc.weightx = 0.75;
        gbc.gridx = 0;
        topPanel.add(profileButton, gbc);

        BufferedImage quitIcon = loadSVG("/quit.svg", 32, 32);
        JButton quitButton = new JButton(new ImageIcon(quitIcon));
        quitButton.setFocusPainted(false);
        quitButton.setBorderPainted(false);
        quitButton.setBackground(new Color(200, 50, 50));
        quitButton.setPreferredSize(new Dimension(50, 50));
        gbc.weightx = 0.25;
        gbc.gridx = 1;
        topPanel.add(quitButton, gbc);

        add(topPanel, BorderLayout.NORTH);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 0, 30));
        infoPanel.setOpaque(false);
        JLabel playersLabel = new JLabel("Online:");
        playersLabel.setForeground(Color.WHITE);
        playersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        infoPanel.add(playersLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel onlineLabel = new JLabel("237 / 250");
        onlineLabel.setForeground(new Color(0, 204, 102));
        onlineLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoPanel.add(onlineLabel);

        add(infoPanel, BorderLayout.CENTER);

        // Нижняя часть с кнопкой Play
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 30, 30));
        bottomPanel.setOpaque(false);

        // ---- Обычная стандартная JButton ----
        playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.setBackground(new Color(61, 61, 61));
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false);
        playButton.setPreferredSize(new Dimension(200, 50));
        playButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Действие кнопки
        playButton.addActionListener(e -> {
            System.out.println("Play clicked!");
            StartClient.start().actionPerformed(null);
        });

        bottomPanel.add(playButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 16)));

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
