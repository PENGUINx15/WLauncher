package me.penguin.wlauncher;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StartClient {
    public static ActionListener start() {
        return e -> {
            try {
                launchClient();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }

    private static void launchClient() throws IOException {

        File clientJar = new File("clients/1.12.2_optifine/versions/OptiFine-1.12.2/OptiFine-1.12.2.jar");
        if (!clientJar.exists()) {
            System.out.println("❌ Ошибка: jar клиента не найден!");
            return;
        }

        File gameDir = new File("clients/1.12.2_optifine");
        if (!gameDir.exists()) {
            System.out.println("❌ Ошибка: папка клиента не найдена!");
            return;
        }

        // Формируем classpath
        StringBuilder classpath = new StringBuilder(clientJar.getPath());
        File libraries = new File("clients/1.12.2_optifine/libraries");
        if (libraries.exists()) {
            addLibraries(classpath, libraries);
        }
        File nativesDir = new File("clients/1.12.2_optifine/natives");
        extractNatives(libraries, nativesDir);

        // Команда запуска Minecraft
        List<String> command = new ArrayList<>();
        command.add(findJava8());
        command.add("-Xmx2G");
        command.add("-Djava.library.path=" + nativesDir.getPath());
        command.add("-cp");
        command.add(classpath.toString());
        command.add("net.minecraft.client.main.Main");
        command.add("--username");
        command.add("PENGUINx13");
        command.add("--version");
        command.add("OptiFine-1.12.2");
        command.add("--gameDir");
        command.add(gameDir.getPath());
        command.add("--server");
        command.add("wcjava.fun");
        command.add("--port");
        command.add("25565");
        command.add("--accessToken");
        command.add("0");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        System.out.println("✅ Minecraft запущен!");

        // (опционально: читаем вывод)
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
    private static void extractNatives(File librariesDir, File nativesDir) throws IOException {
        if (!nativesDir.exists()) nativesDir.mkdirs();

        for (File lib : librariesDir.listFiles()) {
            if (lib.isDirectory()) {
                extractNatives(lib, nativesDir); // рекурсивно
            } else if (lib.getName().endsWith("natives-windows.jar")) {
                java.util.zip.ZipFile zip = new java.util.zip.ZipFile(lib);
                try {
                    java.util.Enumeration<? extends java.util.zip.ZipEntry> entries = zip.entries();
                    while (entries.hasMoreElements()) {
                        java.util.zip.ZipEntry entry = entries.nextElement();
                        if (!entry.isDirectory()) {
                            File out = new File(nativesDir, entry.getName());
                            out.getParentFile().mkdirs();
                            java.io.InputStream in = zip.getInputStream(entry);
                            java.io.FileOutputStream outStream = new java.io.FileOutputStream(out);
                            byte[] buffer = new byte[4096];
                            int len;
                            while ((len = in.read(buffer)) != -1) {
                                outStream.write(buffer, 0, len);
                            }
                            in.close();
                            outStream.close();
                        }
                    }
                } finally {
                    zip.close();
                }
            }
        }
    }
    private static void addLibraries(StringBuilder cp, File folder) {
        for (File f : folder.listFiles()) {
            if (f.isDirectory()) {
                addLibraries(cp, f);
            } else if (f.getName().endsWith(".jar")) {
                cp.append(File.pathSeparator).append(f.getPath());
            }
        }
    }
    public static String findJava8() {
        if (isJava8InPath()) {
            return "java";
        }

        String javaPath = findJava8InStandardLocations();
        if (javaPath != null) {
            return javaPath;
        }

        System.out.println("❌ Java 8 не найдена. Укажите путь вручную:");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    private static boolean isJava8InPath() {
        try {
            Process p = new ProcessBuilder("java", "-version").redirectErrorStream(true).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("1.8")) {
                        return true;
                    }
                }
            }
        } catch (IOException ignored) {}
        return false;
    }

    private static String findJava8InStandardLocations() {
        String[] locations = {
                "C:\\Program Files\\Java\\",
                "C:\\Program Files (x86)\\Java\\",
                "/usr/lib/jvm/",
                "/Library/Java/JavaVirtualMachines/"
        };

        for (String base : locations) {
            File dir = new File(base);
            if (!dir.exists()) continue;
            File[] jdks = dir.listFiles();
            if (jdks == null) continue;

            for (File jdk : jdks) {
                File javaBin = new File(jdk, "bin/java");
                if (!javaBin.exists()) continue;
                if (checkIfJava8(javaBin.getAbsolutePath())) {
                    return javaBin.getAbsolutePath();
                }
            }
        }
        return null;
    }

    private static boolean checkIfJava8(String javaPath) {
        try {
            Process p = new ProcessBuilder(javaPath, "-version").redirectErrorStream(true).start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("1.8")) {
                        return true;
                    }
                }
            }
        } catch (IOException ignored) {}
        return false;
    }
}
