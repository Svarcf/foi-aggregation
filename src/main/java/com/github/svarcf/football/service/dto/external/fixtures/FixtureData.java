package com.github.svarcf.football.service.dto.external.fixtures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.svarcf.football.service.dto.external.SeasonData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixtureData {

    private long id;
    private String utcDate;
    private String matchday;
    private String venue;
    private FixtureTeamData homeTeam;
    private FixtureTeamData awayTeam;
    private FixtureScoreData score;
    private SeasonData season;
    private long competition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUtcDate() {
        return utcDate;
    }

    public void setUtcDate(String utcDate) {
        this.utcDate = utcDate;
    }

    public String getMatchday() {
        return matchday;
    }

    public void setMatchday(String matchday) {
        this.matchday = matchday;
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

    public SeasonData getSeason() {
        return season;
    }

    public void setSeason(SeasonData season) {
        this.season = season;
    }

    public long getCompetition() {
        return competition;
    }

    public void setCompetition(long competition) {
        this.competition = competition;
    }
}
