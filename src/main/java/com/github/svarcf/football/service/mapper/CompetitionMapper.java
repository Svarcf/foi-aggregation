package com.github.svarcf.football.service.mapper;

import com.github.svarcf.football.domain.*;
import com.github.svarcf.football.service.dto.CompetitionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Competition} and its DTO {@link CompetitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompetitionMapper extends EntityMapper<CompetitionDTO, Competition> {



    default Competition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Competition competition = new Competition();
        competition.setId(id);
        return competition;
    }
}
