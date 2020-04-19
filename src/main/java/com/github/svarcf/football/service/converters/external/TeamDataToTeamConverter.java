package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.service.dto.external.TeamData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TeamDataToTeamConverter implements Converter<TeamData, Team> {

    @Override
    public Team convert(TeamData teamData) {
        Team teamModel = new Team();
        if(teamData == null){
            return teamModel;
        }
        teamModel.setId(teamData.getId());
        teamModel.setName(teamData.getName());
        teamModel.setLogo(teamData.getCrestUrl());
        teamModel.setVenueName(teamData.getVenue());
        return teamModel;
    }
}
