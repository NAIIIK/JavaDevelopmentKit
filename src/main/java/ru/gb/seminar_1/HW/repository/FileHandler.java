package ru.gb.seminar_1.HW.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler implements DataHandler {
    private static final String LOG_FILE = "src/main/java/ru/gb/seminar_1/HW/log.txt";

    @Override
    public String getHistory() {
        StringBuilder stringBuilder = new StringBuilder();
        if (Files.exists(Paths.get(LOG_FILE))) {
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(LOG_FILE))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line)
                            .append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void saveHistory(String msg) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            logWriter.write(msg + "\n");
            logWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
