package com.tennis.scheduler.service;

import com.tennis.scheduler.model.Score;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ScoreService {
    private static final String SCORES_FILE = "src/main/resources/scores.json";
    private final ObjectMapper objectMapper;
    private final File scoresFile;

    public ScoreService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.scoresFile = new File(SCORES_FILE);
        
        // Ensure the scores file exists
        if (!scoresFile.exists()) {
            try {
                scoresFile.getParentFile().mkdirs();
                objectMapper.writeValue(scoresFile, new HashMap<Integer, Score>());
            } catch (IOException e) {
                System.err.println("Error creating scores file: " + e.getMessage());
            }
        }
    }

    public Map<Integer, Score> loadScores() {
        try {
            if (scoresFile.exists()) {
                return objectMapper.readValue(scoresFile, 
                    objectMapper.getTypeFactory().constructMapType(Map.class, Integer.class, Score.class));
            }
        } catch (IOException e) {
            System.err.println("Error loading scores: " + e.getMessage());
        }
        return new HashMap<>();
    }

    public void saveScores(Map<Integer, Score> scores) {
        try {
            objectMapper.writeValue(scoresFile, scores);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
            throw new RuntimeException("Failed to save scores", e);
        }
    }

    public void saveScore(int matchId, Score score) {
        Map<Integer, Score> scores = loadScores();
        scores.put(matchId, score);
        saveScores(scores);
    }

    public Score getScore(int matchId) {
        Map<Integer, Score> scores = loadScores();
        return scores.get(matchId);
    }

    public void deleteScore(int matchId) {
        Map<Integer, Score> scores = loadScores();
        scores.remove(matchId);
        saveScores(scores);
    }

    public void clearAllScores() {
        saveScores(new HashMap<>());
    }
}
