package com.cricket;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {
    private static final String FILE_NAME = "match_history.txt";

    public void saveMatch(Match match) {
        // Use try-with-resources for automatic file closing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            writer.write("[" + dtf.format(LocalDateTime.now()) + "] ");
            writer.write(match.getName() + " | Status: " + match.getStatus());

            if (match.getScore() != null && !match.getScore().isEmpty()) {
                Match.Score s = match.getScore().get(0);
                writer.write(" | Score: " + s.getR() + "/" + s.getW() + " (" + s.getO() + " overs)");
            }

            writer.newLine();
            System.out.println("\n\u001B[32mSuccessfully saved to " + FILE_NAME + "\u001B[0m");
        } catch (IOException e) {
            System.err.println("Error saving match history: " + e.getMessage());
        }
    }
}