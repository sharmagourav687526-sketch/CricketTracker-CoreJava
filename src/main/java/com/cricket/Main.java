package com.cricket;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        CricApiClient apiClient = new CricApiClient();
        JsonParser parser = new JsonParser();
        Scanner scanner = new Scanner(System.in);

        // PHASE 1: DISCOVERY & FILTERING
        System.out.println(BLUE + "--- Fetching Live Matches ---" + RESET);
        String rawData = apiClient.fetchLiveScores();
        List<Match> allMatches = parser.parseMatches(rawData);

        // Filter to show only matches that haven't ended yet [cite: 754]
        List<Match> liveOnly = allMatches.stream()
                .filter(m -> !m.getStatus().toLowerCase().contains("won by") &&
                        !m.getStatus().toLowerCase().contains("drawn") &&
                        !m.getStatus().toLowerCase().contains("tied"))
                .toList();

        if (liveOnly.isEmpty()) {
            System.out.println(RED + "No matches are currently live. Showing recent results instead:" + RESET);
            liveOnly = allMatches;
        }

        for (int i = 0; i < liveOnly.size(); i++) {
            System.out.println((i + 1) + ". " + liveOnly.get(i).getName());
        }

        // --- FIXED: ADDED USER INPUT LOGIC ---
        System.out.print("\nSelect a match number to follow live: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > liveOnly.size()) {
            System.out.println(RED + "Invalid selection. Exiting." + RESET);
            return;
        }

        final Match selectedMatch = liveOnly.get(choice - 1);
        final String selectedId = selectedMatch.getId();

        // PHASE 2: DEDICATED TRACKING LOOP [cite: 718]
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println(BLUE + "╔═══════════════════════════════════════════════════════════════════════════╗" + RESET);
                System.out.println(BLUE + "║           TRACKING: " + selectedMatch.getName().split(",")[0] + RESET);
                System.out.println(BLUE + "╚═══════════════════════════════════════════════════════════════════════════╝" + RESET);

                String latestData = apiClient.fetchLiveScores();
                List<Match> updatedMatches = parser.parseMatches(latestData);

                updatedMatches.stream()
                        .filter(m -> m.getId().equals(selectedId))
                        .findFirst()
                        .ifPresent(Main::displayMatchDashboard);

                System.out.println("\nAuto-refreshing in 30 seconds...");
                System.out.println("Press Ctrl+C to stop following.");

            } catch (Exception e) {
                System.out.println(RED + "Tracking Error: " + e.getMessage() + RESET);
            }
        }, 0, 30, TimeUnit.SECONDS);
    }

    private static void displayMatchDashboard(Match match) {
        System.out.println(YELLOW + "Match: " + match.getName() + RESET);
        System.out.println("Venue: " + match.getVenue());

        if (match.getScore() != null && !match.getScore().isEmpty()) {
            Match.Score currentScore = match.getScore().get(0);
            System.out.print(GREEN + "Score: " + currentScore.getR() + "/" + RESET);
            System.out.println(RED + currentScore.getW() + RESET + GREEN + " (" + currentScore.getO() + " overs)" + RESET);
        }

        System.out.println("Status: " + match.getStatus());
        System.out.println("-----------------------------------------------------");
    }
}