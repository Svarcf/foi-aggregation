package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Competition;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.CompetitionRepository;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.TeamData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeamDataToTeamConverter implements Converter<TeamData, Team> {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Team convert(TeamData teamData) {
        Team teamModel = new Team();
        teamModel.setId(teamData.getId());
        teamModel.setName(teamData.getName());
        teamModel.setLogo(teamData.getCrestUrl());
        teamModel.setVenueName(teamData.getVenue());

        Optional<Competition> competition = competitionRepository.findById(teamData.getCompetition());
        competition.ifPresent(teamModel::setCompetition);
        return teamModel;
    }
}
