package com.tennis.scheduler.controller;

import com.tennis.scheduler.model.Score;
import com.tennis.scheduler.service.ScoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class TennisController {

    private final ScoreService scoreService;
    
    @Value("${tennis.score.password}")
    private String scorePassword;

    public TennisController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/validate-password")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> validatePassword(@RequestBody Map<String, String> request) {
        String password = request.get("password");
        boolean isValid = scorePassword.equals(password);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }

    @GetMapping("/api/scores")
    @ResponseBody
    public Map<Integer, Score> getAllScores() {
        return scoreService.loadScores();
    }

    @GetMapping("/api/scores/{matchId}")
    @ResponseBody
    public ResponseEntity<Score> getScore(@PathVariable int matchId) {
        Score score = scoreService.getScore(matchId);
        if (score != null) {
            return ResponseEntity.ok(score);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/scores/{matchId}")
    @ResponseBody
    public ResponseEntity<String> saveScore(@PathVariable int matchId, @RequestBody Score score) {
        try {
            scoreService.saveScore(matchId, score);
            return ResponseEntity.ok("Score saved successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving score: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/scores/{matchId}")
    @ResponseBody
    public ResponseEntity<String> deleteScore(@PathVariable int matchId) {
        try {
            scoreService.deleteScore(matchId);
            return ResponseEntity.ok("Score deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deleting score: " + e.getMessage());
        }
    }

    @DeleteMapping("/api/scores")
    @ResponseBody
    public ResponseEntity<String> clearAllScores() {
        try {
            scoreService.clearAllScores();
            return ResponseEntity.ok("All scores cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error clearing scores: " + e.getMessage());
        }
    }
}
