package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.repository.PlayerRepository;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.aggregators.Aggregator;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class PlayerAggregator implements Aggregator {

    private static final String PLAYER_API_ENDPOINT = "https://www.api-football.com/demo/api/v2/players/team/%s";

    private TeamRepository teamRepository;
    private PlayerRepository playerRepository;
    private ConversionService mvcConversionService;

    public PlayerAggregator(TeamRepository teamRepository, PlayerRepository playerRepository, ConversionService mvcConversionService) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Override
    public void aggregate() {
//        Iterator<Team> teams = teamRepository.findAll().iterator();
//        while(teams.hasNext()){
//            Team team = teams.next();
//            RestTemplate restTemplate = new RestTemplate();
//            SoccerAPIResponseData soccerAPIResponse = restTemplate.getForObject(String.format(PLAYER_API_ENDPOINT, team.getId()), SoccerAPIResponseData.class);
//            Arrays.stream(soccerAPIResponse.getApi().getPlayers()).forEach(playerData -> playerRepository.save(mvcConversionService.convert(playerData, Player.class)));
//        }
    }
}
