package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Player;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDataToPlayerConverter implements Converter<PlayerData, Player> {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Player convert(PlayerData playerData) {
        Player playerModel = new Player();
        if (playerData == null) {
            return playerModel;
        }
        playerModel.setId(playerData.getPlayer_id());
        playerModel.setNumber(playerData.getNumber());
        playerModel.setName(playerData.getPlayer_name());
        playerModel.setPosition(playerData.getPosition());

        Optional<Team> team = teamRepository.findById(playerData.getTeam_id());
        if (team.isPresent()) {
            playerModel.setTeam(team.get());
        }
        return playerModel;
    }
}
