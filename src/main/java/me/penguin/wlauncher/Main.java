package me.penguin.wlauncher;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        System.out.println("WLauncher started!");

        // Запуск UI в EDT
        SwingUtilities.invokeLater(() -> {
            LauncherUI ui = null;
            try {
                ui = new LauncherUI();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            ui.setVisible(true); // Показываем окно
        });
    }
}
