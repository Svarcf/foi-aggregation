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
        teamModel.setId(teamData.getTeam_id());
        teamModel.setName(teamData.getName());
        teamModel.setLogo(teamData.getLogo());
        teamModel.setVenueCity(teamData.getVenue_city());
        teamModel.setVenueName(teamData.getVenue_name());
        return teamModel;
    }
}
