package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Player;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.PlayerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class PlayerDataToPlayerConverter implements Converter<PlayerData, Player> {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Player convert(PlayerData playerData) {
        Player playerModel = new Player();

        if(playerData.getDateOfBirth() != null){
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
            playerModel.setDateOfBirth(LocalDate.parse(playerData.getDateOfBirth(), dateFormat));
        }

        playerModel.setId(playerData.getId());
        playerModel.setName(playerData.getName());
        playerModel.setPosition(playerData.getPosition());
        playerModel.setNationality(playerData.getNationality());

        Optional<Team> team = teamRepository.findById(playerData.getTeam());
        team.ifPresent(value -> playerModel.setTeam(value));
        return playerModel;
    }
}
