package com.cricket;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CricApiClient {
    // Replace the text below with your actual API Key from cricapi.com
    private static final String API_KEY = "211c6400-62e8-4db7-a597-173aa02af452";
    private static final String BASE_URL = "https://api.cricapi.com/v1/currentMatches?apikey=" + API_KEY;

    public String fetchLiveScores() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}