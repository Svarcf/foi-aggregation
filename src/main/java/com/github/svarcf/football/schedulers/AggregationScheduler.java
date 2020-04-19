package com.github.svarcf.football.schedulers;

import com.github.svarcf.football.service.aggregators.Aggregator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AggregationScheduler  {

    private Aggregator fixtureAggregator;
    private Aggregator playerAggregator;
    private Aggregator teamAggregator;

    public AggregationScheduler(Aggregator fixtureAggregator, Aggregator playerAggregator, Aggregator teamAggregator) {
        this.fixtureAggregator = fixtureAggregator;
        this.playerAggregator = playerAggregator;
        this.teamAggregator = teamAggregator;
    }

    @Scheduled(fixedDelay = 200000)
    public void scheduledTask(){
        teamAggregator.aggregate();
//        playerAggregator.aggregate();
        fixtureAggregator.aggregate();
    }

}
