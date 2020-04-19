package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Fixture;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.fixtures.FixtureData;
import com.github.svarcf.football.service.dto.external.fixtures.FixtureScoreData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class FixtureDataToFixtureConverter implements Converter<FixtureData, Fixture> {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Fixture convert(FixtureData fixtureData) {
        Fixture fixtureModel = new Fixture();
        if(fixtureData==null){
            return fixtureModel;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        fixtureModel.setEventDate(LocalDate.parse(fixtureData.getUtcDate(), dateFormat));

        fixtureModel.setId(fixtureData.getId());
//        TODO: fix leagueId issue
//        fixtureModel.setLeagueId(fixtureData.getLeague_id());
        fixtureModel.setVenue(fixtureData.getVenue());
        fixtureModel.setStatusShort(fixtureData.getScore().getWinner());
        FixtureScoreData.Score fulltime = fixtureData.getScore().getFullTime();
        fixtureModel.setScore(fulltime.getHomeTeam() + ":" +  fulltime.getAwayTeam());
        fixtureModel.setRound(fixtureData.getMatchday());

        Optional<Team> homeTeam = teamRepository.findById(fixtureData.getHomeTeam().getId());
        Optional<Team> awayTeam = teamRepository.findById(fixtureData.getAwayTeam().getId());
        if(homeTeam.isPresent() && awayTeam.isPresent()){
            fixtureModel.setHomeTeam(homeTeam.get());
            fixtureModel.setAwayTeam(awayTeam.get());
        }
        return fixtureModel;
    }
}
