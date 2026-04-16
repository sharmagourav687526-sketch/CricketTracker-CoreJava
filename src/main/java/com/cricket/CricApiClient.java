package com.cricket;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

public class CricApiClient {
    // List of your rotating keys
    private final List<String> apiKeys = List.of(
            "211c6400-62e8-4db7-a597-173aa02af452",                  // Key 1
            "c4886b25-690c-4388-b59d-bf27f86eea8d",                 // Key 2
            "97c57e21-291e-400b-bc58-8b5b40441a5c"                  // Key 3
    );

    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchLiveScores() {
        // Randomly pick a key to distribute the "hits"
        String activeKey = apiKeys.get(new Random().nextInt(apiKeys.size()));

        String url = "https://api.cricapi.com/v1/currentMatches?apikey=" + activeKey + "&offset=0";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return null;
        }
    }
}