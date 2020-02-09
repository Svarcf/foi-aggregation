package com.github.svarcf.football.service.dto.external.fixtures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixtureData {

    private long fixture_id;
    private int league_id;
    private String event_date;
    private String round;
    private String statusShort;
    private String venue;
    private FixtureTeamData homeTeam;
    private FixtureTeamData awayTeam;
    private FixtureScoreData score;

    public FixtureData() {
    }

    public long getFixture_id() {
        return fixture_id;
    }

    public void setFixture_id(long fixture_id) {
        this.fixture_id = fixture_id;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public FixtureTeamData getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(FixtureTeamData homeTeam) {
        this.homeTeam = homeTeam;
    }

    public FixtureTeamData getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(FixtureTeamData awayTeam) {
        this.awayTeam = awayTeam;
    }

    public FixtureScoreData getScore() {
        return score;
    }

    public void setScore(FixtureScoreData score) {
        this.score = score;
    }
}
