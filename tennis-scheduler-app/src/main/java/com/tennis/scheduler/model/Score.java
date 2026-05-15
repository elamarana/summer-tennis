package com.tennis.scheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Score {
    private List<SetScore> sets;
    private String winner;
    private String loser;
    private int winnerPoints;
    private int loserPoints;
    private int team1Games;
    private int team2Games;

    public static class SetScore {
        private int team1;
        private int team2;
        private Tiebreaker tiebreaker;

        public SetScore() {}

        public SetScore(int team1, int team2) {
            this.team1 = team1;
            this.team2 = team2;
        }

        public int getTeam1() { return team1; }
        public void setTeam1(int team1) { this.team1 = team1; }
        public int getTeam2() { return team2; }
        public void setTeam2(int team2) { this.team2 = team2; }
        public Tiebreaker getTiebreaker() { return tiebreaker; }
        public void setTiebreaker(Tiebreaker tiebreaker) { this.tiebreaker = tiebreaker; }
    }

    public static class Tiebreaker {
        private int team1;
        private int team2;

        public Tiebreaker() {}

        public Tiebreaker(int team1, int team2) {
            this.team1 = team1;
            this.team2 = team2;
        }

        public int getTeam1() { return team1; }
        public void setTeam1(int team1) { this.team1 = team1; }
        public int getTeam2() { return team2; }
        public void setTeam2(int team2) { this.team2 = team2; }
    }

    public Score() {}

    public Score(List<SetScore> sets, String winner, String loser, int winnerPoints, int loserPoints, int team1Games, int team2Games) {
        this.sets = sets;
        this.winner = winner;
        this.loser = loser;
        this.winnerPoints = winnerPoints;
        this.loserPoints = loserPoints;
        this.team1Games = team1Games;
        this.team2Games = team2Games;
    }

    public List<SetScore> getSets() { return sets; }
    public void setSets(List<SetScore> sets) { this.sets = sets; }
    public String getWinner() { return winner; }
    public void setWinner(String winner) { this.winner = winner; }
    public String getLoser() { return loser; }
    public void setLoser(String loser) { this.loser = loser; }
    public int getWinnerPoints() { return winnerPoints; }
    public void setWinnerPoints(int winnerPoints) { this.winnerPoints = winnerPoints; }
    public int getLoserPoints() { return loserPoints; }
    public void setLoserPoints(int loserPoints) { this.loserPoints = loserPoints; }
    public int getTeam1Games() { return team1Games; }
    public void setTeam1Games(int team1Games) { this.team1Games = team1Games; }
    public int getTeam2Games() { return team2Games; }
    public void setTeam2Games(int team2Games) { this.team2Games = team2Games; }
}
