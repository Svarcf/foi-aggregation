package com.github.svarcf.football.schedulers;

import com.github.svarcf.football.service.aggregators.Aggregator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AggregationScheduler  {

    private Aggregator fixtureAggregator;
    private Aggregator playerAggregator;
    private Aggregator teamAggregator;
    private Aggregator standingAggregator;

    public AggregationScheduler(Aggregator fixtureAggregator, Aggregator playerAggregator, Aggregator teamAggregator, Aggregator standingAggregator) {
        this.fixtureAggregator = fixtureAggregator;
        this.playerAggregator = playerAggregator;
        this.teamAggregator = teamAggregator;
        this.standingAggregator = standingAggregator;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduledTask(){
        teamAggregator.aggregate();
        playerAggregator.aggregate();
        fixtureAggregator.aggregate();
        standingAggregator.aggregate();
    }

}
