package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.config.ApplicationProperties;
import com.github.svarcf.football.domain.Fixture;
import com.github.svarcf.football.repository.FixtureRepository;
import com.github.svarcf.football.service.FoiAbstractRestRequest;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FixtureAggregator extends FoiAbstractRestRequest implements Aggregator  {

    private FixtureRepository fixtureRepository;
    private ConversionService mvcConversionService ;
    private ApplicationProperties applicationProperties;

    public FixtureAggregator(FixtureRepository fixtureRepository, ConversionService mvcConversionService, ApplicationProperties applicationProperties) {
        this.fixtureRepository = fixtureRepository;
        this.mvcConversionService = mvcConversionService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void aggregate() {
        SoccerAPIData soccerAPIData = this.getRequest(applicationProperties.getEndpoints().getFixture(), applicationProperties.getToken()).getBody();
        Arrays.stream(soccerAPIData.getMatches()).forEach(fixtureData -> {
            fixtureData.setCompetition(2021);
            fixtureRepository.save(mvcConversionService.convert(fixtureData, Fixture.class));
        });
    }
}
