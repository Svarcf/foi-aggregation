package com.github.svarcf.football.service.aggregators.impl;

import com.github.svarcf.football.config.ApplicationProperties;
import com.github.svarcf.football.domain.Standing;
import com.github.svarcf.football.repository.StandingRepository;
import com.github.svarcf.football.service.FoiAbstractRestRequest;
import com.github.svarcf.football.service.aggregators.Aggregator;
import com.github.svarcf.football.service.dto.external.SoccerAPIData;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class StandingAggregator extends FoiAbstractRestRequest implements Aggregator {

    private StandingRepository standingRepository;
    private ConversionService mvcConversionService;
    private ApplicationProperties applicationProperties;


    public StandingAggregator(StandingRepository standingRepository, ConversionService mvcConversionService, ApplicationProperties applicationProperties) {
        this.standingRepository = standingRepository;
        this.mvcConversionService = mvcConversionService;
        this.applicationProperties = applicationProperties;
    }

    @Override
    public void aggregate() {
        standingRepository.deleteAll();
        standingRepository.flush();
        SoccerAPIData soccerAPIData = this.getRequest(applicationProperties.getEndpoints().getStanding(), applicationProperties.getToken()).getBody();
        Arrays.stream(soccerAPIData.getStandings()[0].getTable()).forEach(tableData ->
        {
            tableData.setCompetition(2021);
            standingRepository.save(Objects.requireNonNull(mvcConversionService.convert(tableData, Standing.class)));
        });
    }
}
