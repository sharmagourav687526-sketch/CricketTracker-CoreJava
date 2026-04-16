package com.cricket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.ArrayList;

public class JsonParser {
    private static final Gson gson = new Gson();

    public List<Match> parseMatches(String jsonResponse) {
        try {
            // 1. Check if the response itself is null or empty
            if (jsonResponse == null || jsonResponse.isBlank()) {
                return null;
            }

            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

            // 2. Safety Check: Does the JSON actually have a "data" array?
            // If the API key is expired/failed, the key "data" won't exist.
            if (jsonObject == null || !jsonObject.has("data") || jsonObject.get("data").isJsonNull()) {
                return null;
            }

            JsonArray dataArray = jsonObject.getAsJsonArray("data");

            // 3. Convert the "data" array into a List of Match objects
            return gson.fromJson(dataArray, new TypeToken<List<Match>>(){}.getType());

        } catch (Exception e) {
            // Log the error if needed and return null to prevent Main.java from crashing
            return null;
        }
    }
}