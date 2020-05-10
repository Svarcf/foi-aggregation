package com.github.svarcf.football.service.mapper;

import com.github.svarcf.football.domain.*;
import com.github.svarcf.football.service.dto.StandingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Standing} and its DTO {@link StandingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StandingMapper extends EntityMapper<StandingDTO, Standing> {



    default Standing fromId(Long id) {
        if (id == null) {
            return null;
        }
        Standing standing = new Standing();
        standing.setId(id);
        return standing;
    }
}
