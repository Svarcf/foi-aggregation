package com.github.svarcf.football.service.converters.external;

import com.github.svarcf.football.domain.Competition;
import com.github.svarcf.football.service.dto.external.CompetitionData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDataToCompetitionConverter implements Converter<CompetitionData, Competition> {

    @Override
    public Competition convert(CompetitionData competitionData) {
        Competition competitionModel = new Competition();
        competitionModel.setId(competitionData.getId());
        competitionModel.setName(competitionData.getName());
        competitionModel.setCode(competitionData.getCode());
        return competitionModel;
    }
}
