package com.github.svarcf.football.service.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.svarcf.football.service.dto.external.fixtures.FixtureData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SoccerAPIData {

    private CompetitionData competition;
    private FixtureData[] matches;
    private TeamData[] teams;
    private SeasonData season;
    private StandingData standings;

    public CompetitionData getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionData competition) {
        this.competition = competition;
    }

    public FixtureData[] getMatches() {
        return matches;
    }

    public void setMatches(FixtureData[] matches) {
        this.matches = matches;
    }

    public TeamData[] getTeams() {
        return teams;
    }

    public void setTeams(TeamData[] teams) {
        this.teams = teams;
    }

    public SeasonData getSeason() {
        return season;
    }

    public void setSeason(SeasonData season) {
        this.season = season;
    }

    public StandingData getStandings() {
        return standings;
    }

    public void setStandings(StandingData standings) {
        this.standings = standings;
    }
}
