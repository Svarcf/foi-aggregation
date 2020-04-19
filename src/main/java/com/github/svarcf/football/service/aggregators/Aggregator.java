package com.github.svarcf.football.service.aggregators;

public interface Aggregator {

    String FIXTURE_REST_API_ENDPOINT = "http://api.football-data.org/v2/competitions/2021/matches";
    String TEAM_REST_API_ENDPOINT = "http://api.football-data.org/v2/competitions/2021/teams";
    String STANDINGS_REST_API_ENDPOINT = "http://api.football-data.org/v2/competitions/2021/standings";


    void aggregate();
}
