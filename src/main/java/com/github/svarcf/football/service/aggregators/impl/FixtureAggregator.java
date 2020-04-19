package com.github.svarcf.football.service.aggregators.impl;

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

    public FixtureAggregator(FixtureRepository fixtureRepository, ConversionService mvcConversionService) {
        this.fixtureRepository = fixtureRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Override
    public void aggregate() {
        SoccerAPIData soccerAPIData = this.getRequest(FIXTURE_REST_API_ENDPOINT, TOKEN).getBody();
        Arrays.stream(soccerAPIData.getMatches()).forEach(fixtureData -> fixtureRepository.save(mvcConversionService.convert(fixtureData, Fixture.class)));
    }
}
