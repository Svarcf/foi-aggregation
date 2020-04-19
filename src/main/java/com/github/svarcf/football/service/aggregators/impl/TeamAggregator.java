package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.FoiAbstractRestRequest;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TeamAggregator extends FoiAbstractRestRequest implements Aggregator {


    private TeamRepository teamRepository;
    private ConversionService mvcConversionService;

    public TeamAggregator(TeamRepository teamRepository, ConversionService mvcConversionService) {
        this.teamRepository = teamRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Override
    public void aggregate() {
        SoccerAPIData soccerAPIData = this.getRequest(TEAM_REST_API_ENDPOINT, TOKEN).getBody();
        Arrays.stream(soccerAPIData.getTeams()).forEach(teamData -> teamRepository.save(mvcConversionService.convert(teamData, Team.class)));
    }
}
