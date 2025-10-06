package me.penguin.wlauncher;

import me.penguin.wlauncher.UI.*;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LauncherUI extends JFrame {

    public LauncherUI() throws Exception {
        setTitle("WLauncher");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/logo.png"))).getImage());

        BackgroundPanel background = new BackgroundPanel("/background.jpg");
        background.setLayout(new BorderLayout());
        setContentPane(background);

        background.add(new LeftPanel(), BorderLayout.WEST);
        background.add(new CenterPanel(), BorderLayout.CENTER);
        background.add(new RightPanel(), BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LauncherUI ui = new LauncherUI();
                ui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
