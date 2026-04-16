package com.cricket;

import java.util.List;

public class Match {
    private String id; // This is what the getId() method returns
    private String name;
    private String status;
    private String venue;
    private List<Score> score;

    // ADD THIS GETTER
    public String getId() {
        return id;
    }

    public String getName() { return name; }
    public String getStatus() { return status; }
    public String getVenue() { return venue; }
    public List<Score> getScore() { return score; }

    public static class Score {
        private int r;
        private int w;
        private double o;
        public int getR() { return r; }
        public int getW() { return w; }
        public double getO() { return o; }
    }
}