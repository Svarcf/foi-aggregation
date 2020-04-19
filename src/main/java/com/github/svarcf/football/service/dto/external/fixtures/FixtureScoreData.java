package com.github.svarcf.football.service.dto.external.fixtures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixtureScoreData {

    private String winner;
    private Score halfTime;
    private Score fullTime;
    private Score extraTime;
    private Score penalties;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Score getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(Score halfTime) {
        this.halfTime = halfTime;
    }

    public Score getFullTime() {
        return fullTime;
    }

    public void setFullTime(Score fullTime) {
        this.fullTime = fullTime;
    }

    public Score getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(Score extraTime) {
        this.extraTime = extraTime;
    }

    public Score getPenalties() {
        return penalties;
    }

    public void setPenalties(Score penalties) {
        this.penalties = penalties;
    }

    public class Score {
        private int homeTeam;
        private int awayTeam;

        public int getHomeTeam() {
            return homeTeam;
        }

        public void setHomeTeam(int homeTeam) {
            this.homeTeam = homeTeam;
        }

        public int getAwayTeam() {
            return awayTeam;
        }

        public void setAwayTeam(int awayTeam) {
            this.awayTeam = awayTeam;
        }
    }
}
