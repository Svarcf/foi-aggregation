package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Fixture;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.fixtures.FixtureData;
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
        fixtureModel.setEventDate(LocalDate.parse(fixtureData.getEvent_date(), dateFormat));

        fixtureModel.setId(fixtureData.getFixture_id());
//        TODO: fix leagueId issue
//        fixtureModel.setLeagueId(fixtureData.getLeague_id());
        fixtureModel.setVenue(fixtureData.getVenue());
        fixtureModel.setStatusShort(fixtureData.getStatusShort());
        fixtureModel.setScore(fixtureData.getScore().getFulltime());
        fixtureModel.setRound(fixtureData.getRound());

        Optional<Team> homeTeam = teamRepository.findById(fixtureData.getHomeTeam().getTeam_id());
        Optional<Team> awayTeam = teamRepository.findById(fixtureData.getAwayTeam().getTeam_id());
        if(homeTeam.isPresent() && awayTeam.isPresent()){
            fixtureModel.setHomeTeam(homeTeam.get());
            fixtureModel.setAwayTeam(awayTeam.get());
        }
        return fixtureModel;
    }
}
