package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.config.ApplicationProperties;
import com.github.svarcf.football.domain.Player;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.PlayerRepository;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.FoiAbstractRestRequest;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class PlayerAggregator extends FoiAbstractRestRequest implements Aggregator {


    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private ConversionService mvcConversionService;
    private ApplicationProperties applicationProperties;


    public PlayerAggregator(TeamRepository teamRepository, PlayerRepository playerRepository, ConversionService mvcConversionService, ApplicationProperties applicationProperties) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.mvcConversionService = mvcConversionService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void aggregate() {
        for (Team team : teamRepository.findAll()) {
            SoccerAPIData soccerAPIData = this.getRequest(String.format(applicationProperties.getEndpoints().getPlayer(), team.getId()), applicationProperties.getToken()).getBody();
            Arrays.stream(soccerAPIData.getSquad()).forEach(playerData -> {
                playerData.setTeam(team.getId());
                playerRepository.save(mvcConversionService.convert(playerData, Player.class));
            });
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
