package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIResponseData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class TeamAggregator implements Aggregator {

    public static final String TEAM_REST_API_ENDPOINT = "https://api-football-v1.p.rapidapi.com/v2/teams/league/2";

    private TeamRepository teamRepository;
    private ConversionService mvcConversionService;

    public TeamAggregator(TeamRepository teamRepository, ConversionService mvcConversionService) {
        this.teamRepository = teamRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Override
    public void aggregate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.hea
        SoccerAPIResponseData soccerAPIResponse = restTemplate.getForObject(TEAM_REST_API_ENDPOINT, SoccerAPIResponseData.class);
        Arrays.stream(soccerAPIResponse.getApi().getTeams()).forEach(teamData -> teamRepository.save(mvcConversionService.convert(teamData, Team.class)));
    }
}
