package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.config.ApplicationProperties;
import com.github.svarcf.football.domain.Competition;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.CompetitionRepository;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.FoiAbstractRestRequest;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.CompetitionData;
import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TeamAggregator extends FoiAbstractRestRequest implements Aggregator {


    private TeamRepository teamRepository;
    private CompetitionRepository competitionRepository;
    private ConversionService mvcConversionService;
    private ApplicationProperties applicationProperties;

    public TeamAggregator(TeamRepository teamRepository, CompetitionRepository competitionRepository, ConversionService mvcConversionService, ApplicationProperties applicationProperties) {
        this.teamRepository = teamRepository;
        this.competitionRepository = competitionRepository;
        this.mvcConversionService = mvcConversionService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void aggregate() {
        SoccerAPIData soccerAPIData = this.getRequest(applicationProperties.getEndpoints().getTeam(), applicationProperties.getToken()).getBody();
        saveCompetition(soccerAPIData);
        Arrays.stream(soccerAPIData.getTeams()).forEach(teamData ->
        {
            //TODO: from config
            teamData.setCompetition(2021);
            teamRepository.save(mvcConversionService.convert(teamData, Team.class));
        });
    }

    private void saveCompetition(SoccerAPIData soccerAPIData) {
        CompetitionData currentCompetition = soccerAPIData.getCompetition();
        competitionRepository.save(mvcConversionService.convert(currentCompetition, Competition.class));
    }
}
