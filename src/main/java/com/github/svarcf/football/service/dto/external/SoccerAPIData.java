package com.github.svarcf.football.service.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.svarcf.football.service.dto.external.fixtures.FixtureData;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SoccerAPIData {

    private FixtureData[] fixtures;

    private TeamData[] teams;

    private PlayerData[] players;

    public SoccerAPIData() {
    }

    public FixtureData[] getFixtures() {
        return fixtures;
    }

    public void setFixtures(FixtureData[] fixtures) {
        this.fixtures = fixtures;
    }

    public TeamData[] getTeams() {
        return teams;
    }

    public void setTeams(TeamData[] teams) {
        this.teams = teams;
    }

    public PlayerData[] getPlayers() {
        return players;
    }

    public void setPlayers(PlayerData[] players) {
        this.players = players;
    }
}
