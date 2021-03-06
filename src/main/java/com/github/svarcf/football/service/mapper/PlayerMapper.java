package com.github.svarcf.football.service.mapper;

import com.github.svarcf.football.domain.*;
import com.github.svarcf.football.service.dto.PlayerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Player} and its DTO {@link PlayerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PlayerMapper extends EntityMapper<PlayerDTO, Player> {

    default Player fromId(Long id) {
        if (id == null) {
            return null;
        }
        Player player = new Player();
        player.setId(id);
        return player;
    }
}
