package com.cricket;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class JsonParser {
    private static final Gson gson = new Gson();

    public List<Match> parseMatches(String jsonResponse) {
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");

        // Convert the "data" array into a List of Match objects
        return gson.fromJson(dataArray, new TypeToken<List<Match>>(){}.getType());
    }
}