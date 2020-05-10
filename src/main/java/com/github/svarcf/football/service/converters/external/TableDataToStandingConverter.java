package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Competition;
import com.github.svarcf.football.domain.Standing;
import com.github.svarcf.football.domain.Team;
import com.github.svarcf.football.repository.CompetitionRepository;
import com.github.svarcf.football.repository.TeamRepository;
import com.github.svarcf.football.service.dto.external.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TableDataToStandingConverter implements Converter<TableData, Standing> {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Override
    public Standing convert(TableData tableData) {
        Standing standing = new Standing();
        standing.setPosition(tableData.getPosition());
        standing.setDraw(tableData.getDraw());
        standing.setLost(tableData.getLost());
        standing.setWon(tableData.getWon());
        standing.setPoints(tableData.getPoints());

        Optional<Team> team = teamRepository.findById(tableData.getTeam().getId());
        team.ifPresent(standing::setTeam);

        Optional<Competition> competition = competitionRepository.findById(tableData.getCompetition());
        competition.ifPresent(standing::setCompetition);
        return standing;
    }
}
