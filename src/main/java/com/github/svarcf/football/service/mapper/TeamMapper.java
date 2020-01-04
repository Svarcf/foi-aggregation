package com.github.svarcf.football.service.mapper;

import com.github.svarcf.football.domain.*;
import com.github.svarcf.football.service.dto.TeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {


    @Mapping(target = "players", ignore = true)
    @Mapping(target = "removePlayers", ignore = true)
    Team toEntity(TeamDTO teamDTO);

    default Team fromId(Long id) {
        if (id == null) {
            return null;
        }
        Team team = new Team();
        team.setId(id);
        return team;
    }
}
