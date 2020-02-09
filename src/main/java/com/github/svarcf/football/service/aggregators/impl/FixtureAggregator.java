package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.domain.Fixture;
import com.github.svarcf.football.repository.FixtureRepository;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIResponseData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class FixtureAggregator implements Aggregator {

    private static final String FIXTURE_REST_API_ENDPOINT = "https://www.api-football.com/demo/api/v2/fixtures/league/2";

    private FixtureRepository fixtureRepository;
    private ConversionService mvcConversionService ;

    public FixtureAggregator(FixtureRepository fixtureRepository, ConversionService mvcConversionService) {
        this.fixtureRepository = fixtureRepository;
        this.mvcConversionService = mvcConversionService;
    }

    @Override
    public void aggregate() {
        RestTemplate restTemplate = new RestTemplate();
        SoccerAPIResponseData soccerAPIResponse = restTemplate.getForObject(FIXTURE_REST_API_ENDPOINT, SoccerAPIResponseData.class);
        Arrays.stream(soccerAPIResponse.getApi().getFixtures()).forEach(fixtureData -> fixtureRepository.save(mvcConversionService.convert(fixtureData, Fixture.class)));
    }
}
