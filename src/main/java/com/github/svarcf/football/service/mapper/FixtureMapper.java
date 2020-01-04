package com.github.svarcf.football.service.mapper;

import com.github.svarcf.football.domain.*;
import com.github.svarcf.football.service.dto.FixtureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fixture} and its DTO {@link FixtureDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FixtureMapper extends EntityMapper<FixtureDTO, Fixture> {



    default Fixture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fixture fixture = new Fixture();
        fixture.setId(id);
        return fixture;
    }
}
